package com.example.model;

import com.example.enumerate.ClaimStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "claim")
@Getter
@Setter
@ToString(exclude = {"customer", "policy"})
@EqualsAndHashCode(exclude = {"customer", "policy"})

public class Claim {  
	@Id  
	// @GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long claimId;   
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "policyID", referencedColumnName = "policyID", nullable = true)   
	private Policy policy;    
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "customerID", referencedColumnName = "customerID", nullable = true)    
	private Customer customer;   
	
	@Column(name = "claimAmount", nullable = false)    
	private double claimAmount;  
	
	@Enumerated(EnumType.STRING)   
	@Column(name = "status", nullable = false) 
	private ClaimStatus status;
}