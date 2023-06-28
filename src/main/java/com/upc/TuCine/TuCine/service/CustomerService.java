package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.CustomerDto;
import com.upc.TuCine.TuCine.dto.save.Customer.CustomerSaveDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto createCustomer(CustomerSaveDto customerDto);
}
