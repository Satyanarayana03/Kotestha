package com.example.service;

import com.example.exception.PolicyException;
import com.example.feign.CustomerFeign;
import com.example.model.Customer;
import com.example.model.Policy;
import com.example.repository.PolicyRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    CustomerFeign customerFeign;

    public Policy addPolicy(Policy policy){
        try {
            Optional<Customer> customer = customerFeign.getCustomerById(policy.getCustomerId());
            if (customer.isPresent()) {
                policy.setCustomerId(customer.get().getCustomerId());
            }
        } catch (FeignException.NotFound ex) {
            throw new PolicyException("Customer not found with ID: " + policy.getCustomerId());
        }
       if(policyRepository.findById(policy.getPolicyId()).isPresent()) {
            throw new PolicyException("This Policy is already exists");
        }
        else {
            return policyRepository.save(policy);
        }
    }

    public Policy updatePolicy(Policy policy) {
        try {
            Optional<Customer> customer = customerFeign.getCustomerById(policy.getCustomerId());
            if (customer.isPresent()) {
                policy.setCustomerId(customer.get().getCustomerId());
            }
        } catch (FeignException.NotFound ex) {
            throw new PolicyException("Customer not found with ID: " + policy.getCustomerId());
        }
        if(policyRepository.findById(policy.getPolicyId()).isEmpty()) {
            throw new PolicyException("This policy doesn't exists");
        }
        else {
            return policyRepository.save(policy);
        }
    }

    public List<Policy> getAllPolicy() {
        if(policyRepository.findAll().isEmpty()) {
            throw new PolicyException("No policy exists");
        }
        else {
            return policyRepository.findAll();
        }
    }

    public Policy getPolicyById(int id){
        Optional<Policy> byId=policyRepository.findById(id);
        if(byId.isEmpty()){
            throw new PolicyException("id not present");
        }
        else {
            return byId.get();
        }
    }

    public Policy deletePolicy(int id) {
        Optional<Policy> byId=policyRepository.findById(id);
        if(byId.isEmpty()) {
            throw new PolicyException("id not present");
        }
        else {
            policyRepository.deleteById(id);
            return byId.get();
        }

    }
}
