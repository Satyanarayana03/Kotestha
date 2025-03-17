package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Entity
@Data
public class Policy {

    @Id
    private int policyId;
    private String policyName;
    private int premiumAmount;
    private String coverageDetails;
    private LocalDate validityPeriod;
    private int customerId;
}
