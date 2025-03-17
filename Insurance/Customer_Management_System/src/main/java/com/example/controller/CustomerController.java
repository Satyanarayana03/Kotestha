package com.example.controller;

import com.example.Exception.CustomerException;
import com.example.model.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
        }  catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.updateCustomer(customer),HttpStatus.CREATED);
        }
        catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        try {
            return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.FOUND);
        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);

        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/deleteCustomer/{id}")
    public ResponseEntity<Customer> deleteEmployee(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);

        } catch (CustomerException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
