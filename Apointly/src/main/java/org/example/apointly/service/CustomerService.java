package org.example.apointly.service;

import org.example.apointly.dto.CreateCustomerRequest;
import org.example.apointly.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest);
    CustomerDto getCustomerById(Long customerId);
    List<CustomerDto> getAllCustomers();
}