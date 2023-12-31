/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sampleTest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sampleTest.DAO.PersonDAO;
import com.sampleTest.DTO.PersonDto;
import com.sampleTest.model.Person;
import java.io.IOException;
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

    public String savexmlPerson(Person person) {
        Person savedPerson = personDAO.save(person);

        // Convert the saved person data to XML
        try {
            PersonXml personXml = new PersonXml(savedPerson.getId(), savedPerson.getName(), savedPerson.getCity());
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(personXml);
            return xml;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person savePerson(Person person) {
        return personDAO.save(person);
    }

    public Person updatePerson(PersonDto PersonDto, int id) {

        Person person = personDAO.findById(id).orElseThrow(null);
        Person newPerson = mapToEntity(PersonDto);

        person.setName(newPerson.getName());
        person.setCity(newPerson.getCity());

        Person save = personDAO.save(person);

        return save;
    }

    public String updatePersonxml(PersonDto PersonDto, int id) {

        Person person = personDAO.findById(id).orElseThrow(null);
        Person newPerson = mapToEntity(PersonDto);

        person.setName(newPerson.getName());
        person.setCity(newPerson.getCity());

        Person savedPerson = personDAO.save(person);
        try {
            PersonXml personXml = new PersonXml(savedPerson.getId(), savedPerson.getName(), savedPerson.getCity());

            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(personXml);
            return xml;
        } catch (JsonProcessingException e) {
        }
        return null;

    }

    private Person mapToEntity(PersonDto dto) {

        Person person = new Person();

        person.setName(dto.getName());
        person.setCity(dto.getCity());
        return person;
    }

    public Person getPersonById(Integer id) {
        return personDAO.findById(id).orElse(null);
    }

    public String getPersonXmlById(Integer id) {
        Person person = personDAO.findById(id).orElseThrow(null);

        try {
            PersonXml personXml = new PersonXml(person.getId(), person.getName(), person.getCity());

            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(personXml);
            return xml;
        } catch (JsonProcessingException e) {
        }
        return null;
    }

    public List<Person> getAllPersons() {
        return personDAO.findAll();
    }

    public String getAllPersonXML() {
        List<Person> persons = personDAO.findAll();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(persons);
            return xml;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void deletePerson(Integer id) {
        personDAO.deleteById(id);
    }

}
