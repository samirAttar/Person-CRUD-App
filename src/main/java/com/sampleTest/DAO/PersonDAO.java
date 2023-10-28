/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sampleTest.DAO;

import com.sampleTest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 91976
 */
public interface PersonDAO extends JpaRepository<Person, Integer>{

    public Person save(Person person);
     public Person getById(Integer id);

}
