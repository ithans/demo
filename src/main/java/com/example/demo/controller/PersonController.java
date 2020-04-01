package com.example.demo.controller;

import com.example.demo.entiry.Person;
import com.example.demo.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getAllPerson() {
        List<Person> all = personRepository.findAll();
        return all;
    }

    @PostMapping("/{name}")
    public void savePerson(@PathVariable String name) {
        Person p = new Person(name);
        personRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) throws Exception {
        Optional<Person> person = personRepository.findById(Long.valueOf(id));
        if (person.isPresent()){
            personRepository.deleteById(Long.valueOf(id));
        }else{
            throw new Exception("id not exist");
        }
    }

}
