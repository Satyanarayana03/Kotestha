package com.example.service;

import com.example.Exception.CustomerException;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        if(customerRepository.findById(customer.getCustomerId()).isPresent()) {
            throw new CustomerException("This Customer is already exists");
        }
        else {
            return customerRepository.save(customer);
        }
    }

    public Customer updateCustomer(Customer customer) {
        if(customerRepository.findById(customer.getCustomerId()).isEmpty()) {
            throw new CustomerException("This customer doesn't exists");
        }
        else {
            return customerRepository.save(customer);
        }
    }

    public List<Customer> getAllCustomer() {
        if(customerRepository.findAll().isEmpty()) {
            throw new CustomerException("No customer exists");
        }
        else {
            return customerRepository.findAll();
        }

    }

    public Customer getCustomerById(int id){
        Optional<Customer> byId=customerRepository.findById(id);
        if(byId.isEmpty()){
            throw new CustomerException("id not present");
        }
        else {
            return byId.get();
        }

    }

    public Customer deleteCustomer(int id) {
        Optional<Customer> byId=customerRepository.findById(id);
        if(byId.isEmpty()) {
            throw new CustomerException("id not present");
        }
        else {
            customerRepository.deleteById(id);
            return byId.get();
        }

    }

}
