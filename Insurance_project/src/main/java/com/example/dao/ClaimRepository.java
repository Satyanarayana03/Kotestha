 package com.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Claim;
import org.springframework.stereotype.Repository;

 @Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	//Claim save(Optional<Claim> existingData);

}
