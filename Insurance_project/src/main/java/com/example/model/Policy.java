package com.example.model;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {  
	@Id  
	@Column(name = "policyID", nullable = false, unique = true)   
	private String policyID;  
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;  
	
	@Column(name = "premium_amount", nullable = false)   
	private int premiumAmount;   
	
	@Column(name = "coverage_details", length = 255)   
	private String coverageDetails; 
	
	@Column(name = "validity_period", nullable = false)    
	private LocalDate validityPeriod;   
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "customerID", referencedColumnName = "customerID", nullable = true)  
	private Customer customer;  
	
	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)   
	@JsonIgnore  
	private List<Claim> claims;}