package com.sampleTest.UniteTesting.service.service;

import com.sampleTest.DAO.PersonDAO;
import com.sampleTest.model.Person;
import com.sampleTest.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
public class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonDAO personDAO;

    @Test
    public void testSavePerson() {
        Person person = new Person( 1,"John", "Doe");
        when(personDAO.save(person)).thenReturn(person);
        Person savedPerson = personService.savePerson(person);
        assertEquals(person, savedPerson);
    }

    @Test
    public void testGetPersonById() {
        Person person = new Person( 1,"John", "Doe");
        when(personDAO.findById(1)).thenReturn(Optional.of(person));
        Person foundPerson = personService.getPersonById(1);
        assertEquals(person, foundPerson);
    }

    @Test
    public void testGetAllPersons() {
        Person person1 = new Person(1, "John", "Doe");
        Person person2 = new Person( 1,"Jane", "Doe");
        List<Person> allPersons = Arrays.asList(person1, person2);
        when(personDAO.findAll()).thenReturn(allPersons);
        List<Person> foundPersons = personService.getAllPersons();
        assertEquals(allPersons, foundPersons);
    }

    @Test
    public void testDeletePerson() {
        personService.deletePerson(1);
    }
}
