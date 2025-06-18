package org.example.apointly.mapper;

import org.example.apointly.dto.CreateCustomerRequest;
import org.example.apointly.dto.CustomerDto;
import org.example.apointly.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    //Conversion from Customer entity to CustomerDto
    CustomerDto toCustomerDto(Customer customer);

   //    Conversion from CreateCustomerRequest to Customer entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toCustomerEntity(CreateCustomerRequest createCustomerRequest);

    //Conversion from List of Customer entities to List of CustomerDto objects
    // Used to return a list of customers to the controller
    List<CustomerDto> toCustomerDtoList(List<Customer> customers);


}


