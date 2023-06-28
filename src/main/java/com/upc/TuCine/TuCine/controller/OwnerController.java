package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.GenderDto;
import com.upc.TuCine.TuCine.dto.OwnerDto;
import com.upc.TuCine.TuCine.dto.save.Owner.OwnerSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Owners",description = "API de Owners")
@RequestMapping("/api/TuCine/v1")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/owners")
    @Operation(summary = "Obtener la lista de todos los Owners o Propietarios")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron todos los Owners",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OwnerDto.class,type = "array")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron los Owners",
                    content = @Content
            )
        }
    )
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/owners
    //Method: POST
    @Transactional
    @PostMapping("/owners")
    @Operation(summary = "Crear un nuevo Owner o Propietario")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Se creó el Owner",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = OwnerDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No se pudo crear el Owner",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerSaveDto ownerSaveDto){
        return new ResponseEntity<OwnerDto>(ownerService.createOwner(ownerSaveDto), HttpStatus.CREATED);
    }



}
