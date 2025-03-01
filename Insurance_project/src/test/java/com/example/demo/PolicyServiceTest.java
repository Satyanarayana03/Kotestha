package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.service.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.PolicyRepository;
import com.example.exception.PolicyException;
import com.example.exception.CustomerException;
import com.example.model.Policy;

class PolicyServiceTest {

    @InjectMocks
    PolicyService policyService;

    @Mock
    PolicyRepository policyRepository;

    Policy policy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        policy = new Policy();
        policy.setPolicyID("P001");
        policy.setName("Health Insurance");
        policy.setPremiumAmount(5000);
        policy.setCoverageDetails("Covers all medical expenses");
        policy.setValidityPeriod(LocalDate.of(2025, 12, 31));
    }

    @Test
    void testAllPolicies_noPoliciesExist() {
        when(policyRepository.findAll()).thenReturn(Arrays.asList());
        PolicyException exception = assertThrows(PolicyException.class, () -> policyService.allPolicies());
        assertEquals("No policy exists", exception.getMessage());
    }

    @Test
    void testAllPolicies_policiesExist() {
        when(policyRepository.findAll()).thenReturn(Arrays.asList(policy));
        List<Policy> policies = policyService.allPolicies();
        assertEquals(1, policies.size());
        assertEquals("Health Insurance", policies.get(0).getName());
    }

    @Test
    void testAddPolicy_policyExists() {
        when(policyRepository.findById(policy.getPolicyID())).thenReturn(Optional.of(policy));
        CustomerException exception = assertThrows(CustomerException.class, () -> policyService.addPolicy(policy));
        assertEquals("this Policy already exists", exception.getMessage());
    }

    @Test
    void testAddPolicy_policyDoesNotExist() {
        when(policyRepository.findById(policy.getPolicyID())).thenReturn(Optional.empty());
        String result = policyService.addPolicy(policy);
        assertEquals("the new Policy has been created", result);
        verify(policyRepository).save(policy);
    }

    @Test
    void testUpdatePolicies_policyDoesNotExist() {
        when(policyRepository.findById(policy.getPolicyID())).thenReturn(Optional.empty());
        CustomerException exception = assertThrows(CustomerException.class, () -> policyService.updatePolicies(policy));
        assertEquals("this Policy doesn't exists", exception.getMessage());
    }

    @Test
    void testUpdatePolicies_policyExists() {
        when(policyRepository.findById(policy.getPolicyID())).thenReturn(Optional.of(policy));
        String result = policyService.updatePolicies(policy);
        assertEquals("the Policy has been Updated", result);
        verify(policyRepository).save(policy);
    }

    @Test
    void testDeletePolicies_policyDoesNotExist() {
        when(policyRepository.findById(policy.getPolicyID())).thenReturn(Optional.empty());
        CustomerException exception = assertThrows(CustomerException.class, () -> policyService.deletePolicies(policy.getPolicyID()));
        assertEquals("this Policy doesn't exists", exception.getMessage());
    }

    @Test
    void testDeletePolicies_policyExists() {
        when(policyRepository.findById(policy.getPolicyID())).thenReturn(Optional.of(policy));
        String result = policyService.deletePolicies(policy.getPolicyID());
        assertEquals("the Customer has been Updated", result);
        verify(policyRepository).deleteById(policy.getPolicyID());
    }
}
