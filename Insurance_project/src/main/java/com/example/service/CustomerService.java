package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.dao.PolicyRepository;
import com.example.exception.CustomerException;
import com.example.model.Customer;
import com.example.model.Policy;

@Service
public class CustomerService {


	@Autowired
	CustomerRepository repository;

	@Autowired
	PolicyRepository policyRepository;

	public List<Customer> allCustomers()
	{
		return repository.findAll();
	}

	public String addCustomers(Customer customer)

	{


		if(repository.findById(customer.getCustomerID()).isPresent())
		{
			throw new CustomerException("this Custoemr already exists");
		}
		else
		{
			repository.save(customer);
			return "the Customer has been created";
		}
	}
	public String updateCustomers(Customer customer)
	{
		if(repository.findById(customer.getCustomerID()).isEmpty())
		{
			throw new CustomerException("this Customer doesnt exists");
		}
		else
		{

			repository.save(customer);
			return "the Customer has been Updated";
		}
	}
	public String deleteCustomers(int Id)
	{
		if(repository.findById(Id).isEmpty())
		{
			throw new CustomerException("this Customer doesnt exists");
		}
		else
		{

			repository.deleteById(Id);
			return "the Customer has been Updated";
		}
	}


	public List<Customer> getAllData() {
		return repository.findAll();
	}

	public Customer saveCustomerData(Customer customer) {
		return repository.save(customer);
	}

}
