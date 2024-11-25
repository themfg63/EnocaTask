package com.Enoca.Task.controller;

import com.Enoca.Task.dto.CustomerDTO;
import com.Enoca.Task.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.Customer.CUSTOMER)
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(ApiPaths.Customer.ADD)
    public ResponseEntity<?> createUser(@RequestBody CustomerDTO customerDTO){
        try{
            CustomerDTO createdCustomerDto = customerService.addCustomer(customerDTO);
            return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
