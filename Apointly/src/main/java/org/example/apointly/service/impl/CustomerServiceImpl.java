package org.example.apointly.service.impl;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.apointly.dto.CreateCustomerRequest;
import org.example.apointly.dto.CustomerDto;
import org.example.apointly.entity.Customer;
import org.example.apointly.exception.EmailAlreadyExistsException;
import org.example.apointly.exception.ResourceNotFoundException;
import org.example.apointly.mapper.CustomerMapper;
import org.example.apointly.repository.CustomerRepository;
import org.example.apointly.service.CustomerService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        customerRepository.findByEmail(createCustomerRequest.getEmail())
                .ifPresent(existingCustomer -> {
                    throw new EmailAlreadyExistsException("Email '" + createCustomerRequest.getEmail() + "' is already in use by another customer.");
                });

        Customer customerToSave = customerMapper.toCustomerEntity(createCustomerRequest);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return customerMapper.toCustomerDto(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toCustomerDtoList(customers);
    }
}