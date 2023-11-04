package com.sampleTest;

import com.sampleTest.DAO.PersonDAO;
import com.sampleTest.model.Person;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleTestApplicationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @Autowired
    private static RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        restTemplate = new RestTemplate();

    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/persons");
    }

    @Autowired
    private PersonDAO personDAO;

    @Test
    public void savePerson() {
        Person person = new Person(83, "JohnA", "NYC");
        Person response = restTemplate.postForObject(baseUrl, person, Person.class);

        Assertions.assertEquals("JohnA", response.getName());
        Assertions.assertEquals("NYC",response.getCity());
        Assertions.assertEquals(83, response.getId());
       
    }

    // Make sure this method will create new data and after execution it will delete that data.
    @Test
    @Sql(statements = "Insert into person(id,name,city) Values(80,'JohnA','DC')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "Delete from person where id=80", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updatePerson() {

        Person person = new Person(80, "JohnA", "NYC");

        restTemplate.put(baseUrl + "/update/{id}", person, 80);
        Person person1 = personDAO.findById(80).get();

        Assertions.assertAll(
                () -> assertNotNull(person1.getCity()),
                () -> assertEquals("NYC", person.getCity())
        );
    }

    // This method will update the existing data from database. After the test execution data will be save in database
    @Test
    public void updatePersonDB() {
        // Existing data in the database
        int existingPersonId = 56;

        // Create a Person object with the updated data
        Person updatedPerson = new Person(existingPersonId, "C", "DC");

        // Perform an update operation
        restTemplate.put(baseUrl + "/update/{id}", updatedPerson, existingPersonId);

        // Retrieve the updated Person from the database
        Person personFromDatabase = personDAO.findById(existingPersonId).orElse(null);

        // Assertions to verify the update
        Assertions.assertNotNull(personFromDatabase);
        Assertions.assertEquals("DC", personFromDatabase.getCity());
        Assertions.assertEquals("C", personFromDatabase.getName());
    }

    
    @Test
    @Sql(statements = "Insert into person(id,name,city) Values(85,'JohnA','DC')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deletePerson(){
    
        // Prepare: Retrieve the inserted person by ID
    Person person = personDAO.findById(85).orElseThrow(() -> new RuntimeException("Person with id 85 not found"));
    
     // Exercise: Delete the person
    personDAO.delete(person);
    
     // Verify: Ensure the person has been deleted from the database
    Optional<Person> deletedPerson = personDAO.findById(85);
    assertThat(deletedPerson).isEmpty();
    
    }
    
    
    
    
}
