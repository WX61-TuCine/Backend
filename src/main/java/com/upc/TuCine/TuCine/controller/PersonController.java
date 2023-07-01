package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.ContentRatingDto;
import com.upc.TuCine.TuCine.dto.OwnerDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.dto.save.Person.PersonSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Obtener la lista de todas las personas")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron todas las personas",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDto.class,type = "array")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron las personas",
                    content = @Content
            )
    })
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons/{id}/typeUser
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons/{id}/typeUser")
    @Operation(summary = "Obtener el tipo de usuario que es la persona mediante su id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se obtuvo el typeUser de la persona",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TypeUserDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró el tipo de usuario de la persona",
                            content = @Content
                    )
            }
    )
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
    @Operation(summary = "Crear una nueva persona")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Se creó la persona",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PersonSaveDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No se pudo crear la persona",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonSaveDto personSaveDto){
        return new ResponseEntity<>(personService.createPerson(personSaveDto), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/persons/{id}")
    @Operation(summary = "Actualizar una persona")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se actualizó la persona",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PersonSaveDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la persona",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Integer id, @RequestBody PersonSaveDto personSaveDto){
        PersonDto personDto = personService.updatePerson(id, personSaveDto);
        if (personDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/persons/{id}")
    @Operation(summary = "Eliminar una persona")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se eliminó la persona",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PersonDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la persona",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<String> deletePerson(@PathVariable Integer id){
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.OK);
    }

}
