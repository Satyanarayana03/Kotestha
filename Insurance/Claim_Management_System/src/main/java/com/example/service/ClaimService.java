package com.example.service;

import com.example.exception.ClaimException;
import com.example.feign.CustomerFeign;
import com.example.feign.PolicyFeign;
import com.example.model.Claim;
import com.example.model.Customer;
import com.example.model.Policy;
import com.example.repository.ClaimRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    CustomerFeign customerFeign;

    @Autowired
    PolicyFeign policyFeign;

    public Claim addClaim(Claim claim){
        try {
            Optional<Customer> customer = customerFeign.getCustomerById(claim.getCustomerId());
            if (customer.isPresent()) {
                claim.setCustomerId(customer.get().getCustomerId());
            }
        } catch (FeignException.NotFound ex) {
            throw new ClaimException("Customer not found with ID: " + claim.getCustomerId());
        }

        try {
            Optional<Policy> policy = policyFeign.getPolicyById(claim.getPolicyId());
            if (policy.isPresent()) {
                claim.setPolicyId(policy.get().getPolicyId());
            } else {
                throw new ClaimException("No Policy is present with ID: " + claim.getPolicyId());
            }
        } catch (FeignException.NotFound ex) {
            throw new ClaimException("Policy not found with ID: " + claim.getPolicyId());
        }

        if (claimRepository.findById(claim.getClaimId()).isPresent()) {
            throw new ClaimException("This Claim already exists");
        }
        return claimRepository.save(claim);
    }

    public Claim updateClaim(Claim claim){
        try {
            Optional<Customer> customer = customerFeign.getCustomerById(claim.getCustomerId());
            if (customer.isPresent()) {
                claim.setCustomerId(customer.get().getCustomerId());
            }
        } catch (FeignException.NotFound ex) {
            throw new ClaimException("Customer not found with ID: " + claim.getCustomerId());
        }

        try {
            Optional<Policy> policy = policyFeign.getPolicyById(claim.getPolicyId());
            if (policy.isPresent()) {
                claim.setPolicyId(policy.get().getPolicyId());
            } else {
                throw new ClaimException("No Policy is present with ID: " + claim.getPolicyId());
            }
        } catch (FeignException.NotFound ex) {
            throw new ClaimException("Policy not found with ID: " + claim.getPolicyId());
        }

        if(claimRepository.findById(claim.getClaimId()).isEmpty()){
            throw new ClaimException(("This claim doesn't exists"));
        }
        else{
            return claimRepository.save(claim);
        }
    }

    public List<Claim> getAllClaim(){
        if(claimRepository.findAll().isEmpty()){
            throw new ClaimException("No claim exists");
        }
        else {
            return claimRepository.findAll();
        }
    }



}
