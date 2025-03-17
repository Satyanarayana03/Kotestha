package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    private int customerId;
    private String customerName;
    private String customerEmail;
    private Long customerPhonNo;
    private String customerAddress;


}
