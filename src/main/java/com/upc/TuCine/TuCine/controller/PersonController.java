package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.dto.save.Person.PersonSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Persons",description = "API de Persons")
@RequestMapping("/api/TuCine/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons/{id}/typeUser
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons/{id}/typeUser")
    public ResponseEntity<TypeUserDto> getTypeUserByPersonId(@PathVariable("id") Integer id) {
        TypeUserDto typeUserDto= personService.getTypeUserByPersonId(id);
        if (typeUserDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(typeUserDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: POST
    @Transactional
    @PostMapping("/persons")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonSaveDto personSaveDto){

        return new ResponseEntity<>(personService.createPerson(personSaveDto), HttpStatus.CREATED);
    }


}
