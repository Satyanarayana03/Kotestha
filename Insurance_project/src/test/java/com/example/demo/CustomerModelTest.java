package com.example.demo;

import com.example.dao.CustomerRepository;
import com.example.model.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Properties;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CustomerModelTest {


    @Mock
    CustomerRepository repository;

    @InjectMocks
    CustomerModelTest customerModelTest;


    @Test
     void CustomerModelTest() {
        MockitoAnnotations.openMocks(this);
        Customer customer = new Customer();
        customer.setCustomerID(1);
        customer.setName("sharad");
        customer.setEmail("abc@gmail.com");
        customer.setAddress("modinagar");
        customer.setPhone(123456L);


        when(repository.findById(customer.getCustomerID())).thenReturn(Optional.of(customer));
        Optional<Customer> retrievedCustomer = repository.findById(customer.getCustomerID());
        assertEquals(1,retrievedCustomer.get().getCustomerID());
        assertEquals("sharad",retrievedCustomer.get().getName());

    }


}
