package com.example.controller;

import java.util.List;

import java.util.Optional;

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

import com.example.model.Customer;
import com.example.model.Policy;
import com.example.service.CustomerService;
import com.example.service.PolicyService;
import com.example.dao.PolicyRepository;
import com.example.exception.CustomerException;

@RestController
@RequestMapping("/policy")
public class PolicyController {

	  @Autowired
	     PolicyService service;

	    @GetMapping("/allPolicies")
	    public ResponseEntity<List<Policy>> getPolicies() {
	        try
	        {
	        	return new ResponseEntity<>(service.allPolicies(),HttpStatus.FOUND);
	        }catch(CustomerException e){
	        	return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping(value = "/addPolicy", consumes = "application/json")
	    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy)
	    {
	    	try
	    	{
	    		return new ResponseEntity(service.addPolicy(policy),HttpStatus.CREATED);
	    	}
	    	catch(CustomerException e)
	    	{
	    		return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	    	}
	    }
	    @PutMapping("/updatePolicy")
	    public ResponseEntity<Policy> updatePolicy(@RequestBody Policy policy)
	    {
	    	try
	    	{
	    		return new ResponseEntity(service.updatePolicies(policy),HttpStatus.CREATED);
	    	}
	    	catch(CustomerException e)
	    	{
	    		return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
	    	}
	    }

	    @DeleteMapping("/deletePolicy/{Id}")
	    public ResponseEntity<Policy> deletePolicy(@PathVariable String Id )
	    {
	    	try
	    	{
	    		return new ResponseEntity(service.deletePolicies(Id),HttpStatus.OK);
	    	}
	    	catch(CustomerException e)
	    	{
	    		return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
	    	}
	    }

}
