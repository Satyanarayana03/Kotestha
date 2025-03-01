package com.example.controller;

import java.util.List;

import com.example.exception.ClaimException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Claim;
import com.example.service.ClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@GetMapping("/getAllclaim")
	public ResponseEntity<List<Claim>> getClaimData() {
		try {
			return new ResponseEntity<>(claimService.getAllClaimData(), HttpStatus.FOUND);
		} catch (ClaimException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/claim/{id}")
	public ResponseEntity<Claim> getClaimDataById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(claimService.getAllClaimDataById(id), HttpStatus.OK); // Changed to HttpStatus.OK
		} catch (ClaimException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping("/addClaim")
	public ResponseEntity<Claim> addClaimData(@RequestBody Claim claim) {
		try {
			return new ResponseEntity<>(claimService.addClaim(claim), HttpStatus.CREATED);
		} catch (ClaimException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateClaim/{claimId}")
	public ResponseEntity<Claim> updateClaimData(@PathVariable Long claimId, @RequestBody Claim claimDetails) {
		try {
			return new ResponseEntity<>(claimService.updateClaimData(claimId, claimDetails), HttpStatus.OK);
		} catch (ClaimException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
