package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.CustomerRepository;
import com.example.exception.CustomerException;
import com.example.model.Customer;
import org.mockito.internal.matchers.Null;

class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setCustomerID(1);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main St");
        customer.setPhone(1234567890L);
    }

    @Test
    void testAllCustomers_noCustomersExist() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList());
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.allCustomers());
        assertEquals("No customer exists", exception.getMessage());
    }

    @Test
    void testAllCustomers_customersExist() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        List<Customer> customers = customerService.allCustomers();
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
        assertEquals("john.doe@example.com", customers.get(0).getEmail());
        assertEquals("123 Main St", customers.get(0).getAddress());
        assertEquals(1234567890L, customers.get(0).getPhone());
    }

    @Test
    void testAddCustomers_customerExists() {
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.addCustomers(customer));
        assertEquals("this Customer already exists", exception.getMessage());
        assertFalse(customerRepository.findAll().isEmpty());
    }

    @Test
    void testAddCustomers_customerDoesNotExist() {
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.empty());
        String result = customerService.addCustomers(customer);
        assertEquals("the Customer has been created", result);
        verify(customerRepository).save(customer);
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.addCustomers(customer));
        assertEquals("this Customer already exists",exception.getMessage());

    }

    @Test
    void testUpdateCustomers_customerDoesNotExist() {
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.empty());
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.updateCustomers(customer));
        assertEquals("this Customer doesn't exists", exception.getMessage());
    }

    @Test
    void testUpdateCustomers_customerExists() {
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));
        String result = customerService.updateCustomers(customer);
        assertEquals("the Customer has been Updated", result);
        verify(customerRepository).save(customer);
    }

    @Test
    void testDeleteCustomers_customerDoesNotExist() {
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.empty());
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.deleteCustomers(customer.getCustomerID()));
        assertEquals("this Customer doesn't exists", exception.getMessage());
    }

    @Test
    void testDeleteCustomers_customerExists() {
        when(customerRepository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));
        String result = customerService.deleteCustomers(customer.getCustomerID());
        assertEquals("the Customer has been Deleted", result);
        verify(customerRepository).deleteById(customer.getCustomerID());
    }

    @Test
    void testGetAllData() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        List<Customer> customers = customerService.getAllData();
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
    }

    @Test
    void testSaveCustomerData() {
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerService.saveCustomerData(customer);
        assertEquals(customer, savedCustomer);
    }
}
