package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.enumerate.ClaimStatus;
import com.example.model.Claim;
import com.example.model.Customer;
import com.example.model.Policy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClaimModelTest {

    private Claim claim;
    private Policy policy;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        policy = mock(Policy.class);
        customer = mock(Customer.class);

        claim = new Claim();
        claim.setClaimId(1L);
        claim.setPolicy(policy);
        claim.setCustomer(customer);
        claim.setClaimAmount(1000.0);
        claim.setStatus(ClaimStatus.APPROVED);
    }

    @Test
    public void testGetClaimId() {
        assertEquals(1L, claim.getClaimId());
    }

    @Test
    public void testGetPolicy() {
        assertEquals(policy, claim.getPolicy());
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, claim.getCustomer());
    }

    @Test
    public void testGetClaimAmount() {
        assertEquals(1000.0, claim.getClaimAmount());
    }

    @Test
    public void testGetStatus() {
        assertEquals(ClaimStatus.APPROVED, claim.getStatus());
    }

    @Test
    public void testSetClaimId() {
        claim.setClaimId(2L);
        assertEquals(2L, claim.getClaimId());
    }

    @Test
    public void testSetPolicy() {
        Policy newPolicy = mock(Policy.class);
        claim.setPolicy(newPolicy);
        assertEquals(newPolicy, claim.getPolicy());
    }

    @Test
    public void testSetCustomer() {
        Customer newCustomer = mock(Customer.class);
        claim.setCustomer(newCustomer);
        assertEquals(newCustomer, claim.getCustomer());
    }

    @Test
    public void testSetClaimAmount() {
        claim.setClaimAmount(2000.0);
        assertEquals(2000.0, claim.getClaimAmount());
    }

    @Test
    public void testSetStatus() {
        claim.setStatus(ClaimStatus.REJECTED);
        assertEquals(ClaimStatus.REJECTED, claim.getStatus());
    }
}
