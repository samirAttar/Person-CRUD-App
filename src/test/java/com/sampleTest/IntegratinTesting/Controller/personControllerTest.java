package com.sampleTest.IntegratinTesting.Controller;

import com.sampleTest.DAO.PersonDAO;
import com.sampleTest.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class personControllerTest {

//    @LocalServerPort
//    private int port;
//
//    private String baseUrl="http://localhost";
//
//    private static RestTemplate restTemplate;
//
//    @Autowired
//    private PersonDAO personDAO;
//
//
//    @BeforeAll
//    public static void init(){
//        restTemplate=new RestTemplate();
//    }
//
//    @BeforeEach
//    public void setUp(){
//        baseUrl=baseUrl.concat(":").concat(port+"").concat("/api/persons");
//    }
//
//    @Test
//    public void testAddProduct(){
//        Person person = new Person(10, "John", "nyc");
//        Person respo = restTemplate.postForObject(baseUrl, person, Person.class);
//        assertEquals(person, respo);
//        assertEquals(10,personDAO.findAll().size());
//    }


}
