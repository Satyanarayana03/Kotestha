package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Agent {
    @Id
    private int agentId;
    private String name;
    private String contactInfo;
    private String assignedPolicies;

}
