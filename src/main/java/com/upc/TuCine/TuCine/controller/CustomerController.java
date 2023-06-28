package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.ContentRatingDto;
import com.upc.TuCine.TuCine.dto.CustomerDto;
import com.upc.TuCine.TuCine.dto.save.Customer.CustomerSaveDto;
import com.upc.TuCine.TuCine.service.CustomerService;
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
@Tag(name = "Customer", description = "API de Customers")
@RequestMapping("/api/TuCine/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/customers")
    @Operation(summary = "Obtener todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo la lista de clientes",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDto.class, type = "array"))
                    }),

    })
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: POST
    @Transactional
    @PostMapping("/customers")
    @Operation(summary = "Crear un nuevo cliente ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se creó el cliente",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "El cliente no se pudo crear",
                    content = @Content),
    })
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerSaveDto customerSaveDto){
        return new ResponseEntity<CustomerDto>(customerService.createCustomer(customerSaveDto), HttpStatus.CREATED);
    }


}
