package com.sampleTest;

import com.sampleTest.DAO.PersonDAO;
import com.sampleTest.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
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
        Person person = new Person("JohnA", "NYC");
        Person response = restTemplate.postForObject(baseUrl, person, Person.class);

        Assertions.assertEquals("JohnA", response.getName());
        // Assertions.assertEquals(26, personDAO.findAll().size());

    }
}
