package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.dao.ClaimRepository;
import com.example.dao.CustomerRepository;
import com.example.dao.PolicyRepository;
import com.example.exception.ClaimException;
import com.example.model.Claim;
import com.example.model.Customer;
import com.example.model.Policy;
import com.example.enumerate.ClaimStatus;
import com.example.service.ClaimService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClaimServiceTest {

    @InjectMocks
    private ClaimService claimService;

    @Mock
    private ClaimRepository claimRepo;

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private PolicyRepository policyRepo;

    private Claim claim;
    private Customer customer;
    private Policy policy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setCustomerID(1);

        policy = new Policy();
        policy.setPolicyID("1L");

        claim = new Claim();
        claim.setClaimId(1L);
        claim.setCustomer(customer);
        claim.setPolicy(policy);
        claim.setClaimAmount(1000.0);
        claim.setStatus(ClaimStatus.APPROVED);
    }

    @Test
    public void testGetAllClaimData() {
        when(claimRepo.findAll()).thenReturn(Arrays.asList(claim));
        assertFalse(claimService.getAllClaimData().isEmpty());
    }

    @Test
    public void testGetAllClaimData_NoClaims() {
        when(claimRepo.findAll()).thenReturn(Arrays.asList());
        assertThrows(ClaimException.class, () -> claimService.getAllClaimData());
    }

    @Test
    public void testGetAllClaimDataById() {
        when(claimRepo.findById(1L)).thenReturn(Optional.of(claim));
        assertEquals(claim, claimService.getAllClaimDataById(1L));
    }


    @Test
    public void testGetAllClaimDataById_NoClaim() {
        when(claimRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClaimException.class, () -> claimService.getAllClaimDataById(1L));
    }

    @Test
    public void testAddClaim() {
        when(claimRepo.findById(1L)).thenReturn(Optional.empty());
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(policyRepo.findById("1L")).thenReturn(Optional.of(policy));
        when(claimRepo.save(claim)).thenReturn(claim);

        assertEquals(claim, claimService.addClaim(claim));
    }

    @Test
    public void testAddClaim_ExistingClaim() {
        when(claimRepo.findById(1L)).thenReturn(Optional.of(claim));
        assertThrows(ClaimException.class, () -> claimService.addClaim(claim));
    }

    @Test
    public void testAddClaim_NoCustomer() {
        when(claimRepo.findById(1L)).thenReturn(Optional.empty());
        when(customerRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ClaimException.class, () -> claimService.addClaim(claim));
    }

    @Test
    public void testAddClaim_NoPolicy() {
        when(claimRepo.findById(1L)).thenReturn(Optional.empty());
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(policyRepo.findById("1L")).thenReturn(Optional.empty());
        assertThrows(ClaimException.class, () -> claimService.addClaim(claim));
    }

    @Test
    public void testUpdateClaimData() {
        when(claimRepo.findById(1L)).thenReturn(Optional.of(claim));
        when(policyRepo.findById("1L")).thenReturn(Optional.of(policy));
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(claimRepo.save(claim)).thenReturn(claim);

        Claim updatedClaim = new Claim();
        updatedClaim.setClaimAmount(2000.0);
        updatedClaim.setStatus(ClaimStatus.REJECTED);
        updatedClaim.setPolicy(policy);
        updatedClaim.setCustomer(customer);

        Claim result = claimService.updateClaimData(1L, updatedClaim);
        assertEquals(2000.0, result.getClaimAmount());
        assertEquals(ClaimStatus.REJECTED, result.getStatus());
    }

    @Test
    public void testUpdateClaimData_NoClaim() {
        when(claimRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ClaimException.class, () -> claimService.updateClaimData(1L, claim));
    }

}
