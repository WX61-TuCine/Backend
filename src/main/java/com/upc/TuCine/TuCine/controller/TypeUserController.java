package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.TicketDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.dto.save.TypeUser.TypeUserSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
import com.upc.TuCine.TuCine.service.TypeUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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
@Tag(name = "TypeUsers",description = "API de TypeUsers")
@RequestMapping("/api/TuCine/v1")

public class TypeUserController {

    @Autowired
    private TypeUserService typeUserService;

    public TypeUserController(TypeUserService typeUserService) {
        this.typeUserService = typeUserService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/typeUsers")
    @Operation(summary = "Obtener todos los TypeUsers", description = "Obtiene la lista de todos los tipos de usuarios disponibles")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se obtuvo la lista de tipos de usuarios",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TypeUserDto.class,type = "array")
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron los tipos de usuario",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<List<TypeUserDto>> getAllTypeUsers() {
        return new ResponseEntity<List<TypeUserDto>>(typeUserService.getAllTypeUsers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/typeUsers
    //Method: POST
    @Transactional
    @PostMapping("/typeUsers")
    @Operation(summary = "Crear un nuevo TypeUser", description = "Crea un nuevo tipo de usuario con la información proporcionada")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Se creó el tipo de usuario",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TypeUserDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No se pudo crear el tipo de usuario",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<TypeUserDto> createTypeUser(@RequestBody TypeUserSaveDto typeUserSaveDto){
        TypeUserDto createdTypeUserDto= typeUserService.createTypeUser(typeUserSaveDto);
        return new ResponseEntity<>(createdTypeUserDto, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/typeUsers/{id}")
    @Operation(summary = "Actualizar un TypeUser", description = "Actualiza un tipo de usuario con la información proporcionada")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se actualizó el tipo de usuario",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TypeUserDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se pudo actualizar el tipo de usuario",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<TypeUserDto> updateTypeUser(@PathVariable Integer id, @RequestBody TypeUserSaveDto typeUserSaveDto){
        TypeUserDto updatedTypeUserDto= typeUserService.updateTypeUser(id,typeUserSaveDto);
        return new ResponseEntity<>(updatedTypeUserDto, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/typeUsers/{id}")
    @Operation(summary = "Eliminar un TypeUser", description = "Elimina un tipo de usuario con la información proporcionada")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se eliminó el tipo de usuario",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TypeUserDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se pudo eliminar el tipo de usuario",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<String> deleteTypeUser(@PathVariable Integer id){
        return new ResponseEntity<>(typeUserService.deleteTypeUser(id), HttpStatus.OK);
    }

}
