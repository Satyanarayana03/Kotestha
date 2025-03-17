package com.example.feign;

import com.example.model.Policy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="POLICY-MANAGEMENT-SYSTEM")
public interface PolicyFeign {

    @GetMapping("policy/{id}")
    public Optional<Policy> getPolicyById(@PathVariable("id") int id);

}
