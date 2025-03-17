package com.example.controller;

import com.example.exception.ClaimException;
import com.example.model.Claim;
import com.example.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claim")
public class ClaimController {
    @Autowired
    ClaimService claimService;

    @PostMapping("/addClaim")
    public ResponseEntity<Claim> addClaim(@RequestBody Claim claim){
        try{
            return new ResponseEntity<>(claimService.addClaim(claim), HttpStatus.CREATED);
        } catch (ClaimException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateClaim")
    public ResponseEntity<Claim> updateClaim(@RequestBody Claim claim){
        try{
            return new ResponseEntity<>(claimService.updateClaim(claim),HttpStatus.CREATED);
        } catch (ClaimException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allClaim")
    public ResponseEntity<List<Claim>> getAllClaim(){
        try{
            return new ResponseEntity<>(claimService.getAllClaim(),HttpStatus.FOUND);
        } catch (ClaimException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
