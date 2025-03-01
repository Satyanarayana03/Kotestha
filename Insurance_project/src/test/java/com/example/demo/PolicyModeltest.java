package com.example.demo;


import com.example.dao.PolicyRepository;
import com.example.model.Policy;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PolicyModeltest {

    @Mock
    PolicyRepository repository;

    @InjectMocks
    Policy policy;

@Test
    void policyModelTest() {
        MockitoAnnotations.openMocks(this);
        Policy policy = new Policy();
        policy.setPolicyID("123");
        policy.setName("abc");
        policy.setCoverageDetails("hello hello");
        policy.setPremiumAmount(10000);
        policy.setValidityPeriod(LocalDate.of(2026, 10, 1));



    when(repository.findById(policy.getPolicyID())).thenReturn(Optional.of(policy));
        Optional<Policy> retrievedPolciy = repository.findById(policy.getPolicyID());
        assertEquals("123",retrievedPolciy.get().getPolicyID());

    }
}
