package com.example.feign;

import com.example.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="CUSTOMER-MANAGEMENT-SYSTEM")
public interface CustomerFeign {

    @GetMapping("customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") int id);

}
