/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sampleTest.controller;

import com.sampleTest.DTO.PersonDto;
import com.sampleTest.model.Person;
import com.sampleTest.service.PersonService;
import java.lang.System.Logger;
import java.util.List;
import java.util.logging.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 91976
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    //http://localhost:8085/api/persons
    @Autowired
    private PersonService personService;


    
    
    @PostMapping("/savePerson")
    public ResponseEntity<String> savexmlPerson(@RequestBody Person person) {
        String xml = personService.savexmlPerson(person);
        return new ResponseEntity<>(xml, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    
    @PutMapping("/update/{id}")
    public Person updatePerson(@RequestBody PersonDto person, @PathVariable int id) {
        return personService.updatePerson(person,id);
    }

    //http://localhost:8085/api/persons/
    @GetMapping("/")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
        return new ResponseEntity("Data is deleted", HttpStatus.GONE);
    }

}
