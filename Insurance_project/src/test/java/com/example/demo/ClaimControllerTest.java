package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import com.example.controller.ClaimController;
import com.example.exception.ClaimException;
import com.example.model.Claim;
import com.example.service.ClaimService;
import com.example.enumerate.ClaimStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ClaimControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClaimController claimController;

    @Mock
    private ClaimService claimService;

    private Claim claim;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(claimController).build();

        claim = new Claim();
        claim.setClaimId(1L);
        claim.setClaimAmount(1000.0);
        claim.setStatus(ClaimStatus.APPROVED);
    }

    @Test
    public void testGetClaimData() throws Exception {
        when(claimService.getAllClaimData()).thenReturn(Arrays.asList(claim));

        mockMvc.perform(get("/claim/getAllclaim"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].claimId").value(1L))
                .andExpect(jsonPath("$[0].claimAmount").value(1000.0))
                .andExpect(jsonPath("$[0].status").value("APPROVED"));
    }

    @Test
    public void testGetClaimData_NoClaims() throws Exception {
        when(claimService.getAllClaimData()).thenThrow(new ClaimException("No claim available"));

        mockMvc.perform(get("/claim/getAllclaim"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No claim available"));
    }

    @Test
    public void testGetClaimDataById() throws Exception {
        when(claimService.getAllClaimDataById(1L)).thenReturn(claim);

        mockMvc.perform(get("/claim/claim/{id}", 1L))
                .andExpect(status().isOk())  // Changed from isFound() to isOk()
                .andExpect(jsonPath("$.claimId").value(1L))
                .andExpect(jsonPath("$.claimAmount").value(1000.0))
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }


    @Test
    public void testGetClaimDataById_NoClaim() throws Exception {
        when(claimService.getAllClaimDataById(1L)).thenThrow(new ClaimException("No Such claim available with ID: 1"));

        mockMvc.perform(get("/claim/claim/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No Such claim available with ID: 1"));
    }

    @Test
    public void testAddClaimData() throws Exception {
        when(claimService.addClaim(any(Claim.class))).thenReturn(claim);

        mockMvc.perform(post("/claim/addClaim")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"claimId\":1,\"claimAmount\":1000.0,\"status\":\"APPROVED\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.claimId").value(1L))
                .andExpect(jsonPath("$.claimAmount").value(1000.0))
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    @Test
    public void testAddClaimData_ExistingClaim() throws Exception {
        when(claimService.addClaim(any(Claim.class))).thenThrow(new ClaimException("this claim already exists"));

        mockMvc.perform(post("/claim/addClaim")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"claimId\":1,\"claimAmount\":1000.0,\"status\":\"APPROVED\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("this claim already exists"));
    }

    @Test
    public void testUpdateClaimData() throws Exception {
        Claim updatedClaim = new Claim();
        updatedClaim.setClaimId(1L);
        updatedClaim.setClaimAmount(2000.0);
        updatedClaim.setStatus(ClaimStatus.REJECTED);

        when(claimService.updateClaimData(eq(1L), any(Claim.class))).thenReturn(updatedClaim);

        mockMvc.perform(put("/claim/updateClaim/{claimId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"claimAmount\":2000.0,\"status\":\"REJECTED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.claimId").value(1L))
                .andExpect(jsonPath("$.claimAmount").value(2000.0))
                .andExpect(jsonPath("$.status").value("REJECTED"));
    }


    @Test
    public void testUpdateClaimData_NoClaim() throws Exception {
        when(claimService.updateClaimData(eq(1L), any(Claim.class))).thenThrow(new ClaimException("No Such claim available with ID: 1"));

        mockMvc.perform(put("/claim/updateClaim/{claimId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"claimAmount\":2000.0,\"status\":\"REJECTED\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No Such claim available with ID: 1"));
    }
}
