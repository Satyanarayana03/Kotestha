package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.exception.ClaimException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dao.ClaimRepository;
import com.example.dao.CustomerRepository;
import com.example.dao.PolicyRepository;
import com.example.model.Claim;
import com.example.model.Customer;
import com.example.model.Policy;

@Service
public class ClaimService {

	@Autowired
	private ClaimRepository claimRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PolicyRepository policyRepo;

	@Transactional
	public List<Claim> getAllClaimData() {
		if (claimRepo.findAll().isEmpty()) {
			throw new ClaimException("No claim available");
		} else {
			return claimRepo.findAll();
		}
	}

	@Transactional
	public Claim getAllClaimDataById(Long id) {
		return claimRepo.findById(id).orElseThrow(() -> new ClaimException("No Such claim available with ID: " + id));
	}

	@Transactional
	public Claim addClaim(Claim claim) {
		if (claimRepo.findById(claim.getClaimId()).isPresent()) {
			throw new ClaimException("this claim already exists");
		}
		Optional<Customer> customerOptional = customerRepo.findById(claim.getCustomer().getCustomerID());
		if (customerOptional.isPresent()) {
			claim.setCustomer(customerOptional.get());
		}
		else {
			throw new ClaimException("no such customer present");
		}
		Optional<Policy> policyOptional = policyRepo.findById(claim.getPolicy().getPolicyID());
		if (policyOptional.isPresent()) {
			claim.setCustomer(customerOptional.get());
		}
		else {
			throw new ClaimException("no such policy present");
		}
		return claimRepo.save(claim);

	}



	@Transactional
	public Claim updateClaimData(Long claimId, Claim claimDetails) {
		Optional<Claim> existingData = claimRepo.findById(claimId);
		if (existingData.isPresent()) {
			Claim existingClaim = existingData.get();

			existingClaim.setClaimAmount(claimDetails.getClaimAmount());
			existingClaim.setStatus(claimDetails.getStatus());

			System.out.println("Updated Claim Amount: " + existingClaim.getClaimAmount()); // Debug statement

			if (claimDetails.getPolicy() != null) {
				Policy policy = policyRepo.findById(claimDetails.getPolicy().getPolicyID())
						.orElseThrow();
				existingClaim.setPolicy(policy);
			}

			if (claimDetails.getCustomer() != null) {
				Customer customer = customerRepo.findById(claimDetails.getCustomer().getCustomerID())
						.orElseThrow();
				existingClaim.setCustomer(customer);
			}

			return claimRepo.save(existingClaim);
		} else {
			throw new ClaimException("No Such claim available with ID: " + claimId);
		}
	}
}
