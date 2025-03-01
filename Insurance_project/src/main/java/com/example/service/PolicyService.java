package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.dao.PolicyRepository;
import com.example.exception.CustomerException;
import com.example.model.Customer;
import com.example.model.Policy;

@Service
public class PolicyService {


	@Autowired
	PolicyRepository repository;

	public List<Policy> allPolicies()
	{
		return repository.findAll();
	}

	public String addPolicy(Policy policy)
	{
		if(repository.findById(policy.getPolicyID()).isPresent())
		{
			throw new CustomerException("this Policy already exists");
		}
		else
		{
			repository.save(policy);
			return "the new Policy has been created";
		}
	}
	public String updatePolicies(Policy policy)
	{
		if(repository.findById(policy.getPolicyID()).isEmpty())
		{
			throw new CustomerException("this Policy doesnt exists");
		}
		else
		{

			repository.save(policy);
			return "the Policy has been Updated";
		}
	}
	public String deletePolicies(String Id)
	{
		if(repository.findById(Id).isEmpty())
		{
			throw new CustomerException("this Policy doesnt exists");
		}
		else
		{

			repository.deleteById(Id);
			return "the Customer has been Updated";
		}
	}

}
