package com.example.model;

import com.example.enumerate.ClaimStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Claim {
    @Id
    private int claimId;
    private int claimAmount;
    private int customerId;
    private int policyId;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;
}
