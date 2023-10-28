/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sampleTest.service;

import com.sampleTest.DAO.PersonDAO;
import com.sampleTest.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 91976
 */
@Service
public class PersonService {
    
    @Autowired
    private PersonDAO personDAO;
    
    
    public Person savePerson(Person person) {
        return personDAO.save(person);
    }
    
    public Person getPersonById(Integer id) {
        return personDAO.findById(id).orElse(null);
    }
    
    
    public List<Person> getAllPersons() {
        return personDAO.findAll();
    }
    
    public void deletePerson(Integer id) {
        personDAO.deleteById(id);
    }
    
}
