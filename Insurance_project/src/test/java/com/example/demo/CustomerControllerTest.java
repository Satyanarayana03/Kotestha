package com.example.demo;

import com.example.controller.CustomerController;
import com.example.exception.CustomerException;
import com.example.model.Customer;
import com.example.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        customer = new Customer();
        customer.setCustomerID(1);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main St, Springfield, USA");
        customer.setPhone(1234567890L);
    }

    @Test
    void testGetUsers() throws CustomerException {
        List<Customer> customers = Arrays.asList(customer);
        when(service.allCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = controller.getUsers();
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void testAddCustomer() throws CustomerException {
        when(service.addCustomers(customer)).thenReturn("Customer added successfully");

        ResponseEntity<String> response = controller.addCustomer(customer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Customer added successfully", response.getBody());
    }

    @Test
    void testUpdateCustomer() throws CustomerException {
        when(service.updateCustomers(customer)).thenReturn("Customer updated successfully");

        ResponseEntity<String> response = controller.updateCustomer(customer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Customer updated successfully", response.getBody());
    }

    @Test
    void testDeleteCustomer() throws CustomerException {
        int customerId = 1;
        when(service.deleteCustomers(customerId)).thenReturn("Customer deleted successfully");

        ResponseEntity<String> response = controller.deleteCustomer(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer deleted successfully", response.getBody());
    }

    @Test
    void testGetUsersException() throws CustomerException {
        when(service.allCustomers()).thenThrow(new CustomerException("No customers found"));

        ResponseEntity<List<Customer>> response = controller.getUsers();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No customers found", response.getBody());
    }

    @Test
    void testAddCustomerException() throws CustomerException {
        when(service.addCustomers(customer)).thenThrow(new CustomerException("Customer could not be added"));

        ResponseEntity<String> response = controller.addCustomer(customer);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer could not be added", response.getBody());
    }

    @Test
    void testUpdateCustomerException() throws CustomerException {
        when(service.updateCustomers(customer)).thenThrow(new CustomerException("Customer could not be updated"));

        ResponseEntity<String> response = controller.updateCustomer(customer);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer could not be updated", response.getBody());
    }

    @Test
    void testDeleteCustomerException() throws CustomerException {
        int customerId = 1;
        when(service.deleteCustomers(customerId)).thenThrow(new CustomerException("Customer could not be deleted"));

        ResponseEntity<String> response = controller.deleteCustomer(customerId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer could not be deleted", response.getBody());
    }
}
