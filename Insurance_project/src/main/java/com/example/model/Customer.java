package com.example.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"claims", "policies"})
@EqualsAndHashCode(exclude = {"claims", "policies"})
public class Customer {    
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int customerID; 
	
	@Column(name = "name", nullable = false, length = 50)  
	private String name;  
	
	@Column(name = "email", nullable = false, length = 100)  
	private String email;  
	
	@Column(name = "address", nullable = true, length = 200)  
	private String address;   
	
	@Column(name = "phone", nullable = false, length = 10) 
	private long phone;   
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore  
	private List<Policy> policies;   
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)   
	@JsonIgnore   
	private List<Claim> claims;}