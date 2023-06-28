package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.CustomerDto;
import com.upc.TuCine.TuCine.dto.save.Customer.CustomerSaveDto;
import com.upc.TuCine.TuCine.service.CustomerService;
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
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/customers
    //Method: POST
    @Transactional
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerSaveDto customerSaveDto){
        return new ResponseEntity<CustomerDto>(customerService.createCustomer(customerSaveDto), HttpStatus.CREATED);
    }


}
