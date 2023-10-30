package com.sampleTest.UniteTesting.service.controller;

import com.sampleTest.controller.PersonController;
import com.sampleTest.model.Person;
import com.sampleTest.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

}
