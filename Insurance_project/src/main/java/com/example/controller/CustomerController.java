package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.dao.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CustomerRepository;
import com.example.exception.CustomerException;
import com.example.model.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService service;



    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> getUsers() {
        try {
            return new ResponseEntity<>(service.allCustomers(), HttpStatus.FOUND);
        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(service.addCustomers(customer), HttpStatus.CREATED);
        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(service.updateCustomers(customer), HttpStatus.CREATED);
        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCustomer/{Id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int Id) {
        try {
            return new ResponseEntity<>(service.deleteCustomers(Id), HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
