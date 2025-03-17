package com.example.controller;


import com.example.exception.PolicyException;
import com.example.feign.CustomerFeign;
import com.example.model.Customer;
import com.example.model.Policy;
import com.example.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    PolicyService policyService;

    @PostMapping("/addPolicy")
    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
        try {
            return new ResponseEntity<>(policyService.addPolicy(policy), HttpStatus.CREATED);
        }  catch (PolicyException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatePolicy")
    public ResponseEntity<Policy> updatePolicyr(@RequestBody Policy policy) {
        try {
            return new ResponseEntity<>(policyService.updatePolicy(policy),HttpStatus.CREATED);
        }
        catch (PolicyException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allPolicy")
    public ResponseEntity<List<Policy>> getAllPolicyr() {
        try {
            return new ResponseEntity<>(policyService.getAllPolicy(),HttpStatus.FOUND);
        } catch (PolicyException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(policyService.getPolicyById(id), HttpStatus.OK);

        } catch (PolicyException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/deletePolicy/{id}")
    public ResponseEntity<Policy> deleteEmployee(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(policyService.deletePolicy(id), HttpStatus.OK);

        } catch (PolicyException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }







}
