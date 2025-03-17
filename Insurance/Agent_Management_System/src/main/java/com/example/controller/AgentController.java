package com.example.controller;

import com.example.exception.AgentException;
import com.example.model.Agent;
import com.example.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    AgentService agentService;

    @PostMapping("/addAgent")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        try {
            return new ResponseEntity<>(agentService.addAgent(agent), HttpStatus.CREATED);
        } catch (AgentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateAgent")
    public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) {
        try {
            return new ResponseEntity<>(agentService.updateAgent(agent),HttpStatus.CREATED);
        } catch (AgentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allAgent")
    public  ResponseEntity<List<Agent>> getAllAgent() {
        try {
            return new ResponseEntity<>(agentService.getAllAgent(),HttpStatus.OK);
        } catch (AgentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Agent> findAgentbyId(@PathVariable int id) {
        try {
            return new ResponseEntity<>(agentService.findAgentbyId(id),HttpStatus.FOUND);
        } catch (AgentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAgent/{id}")
    public ResponseEntity<Agent> deleteAgent(@PathVariable int id) {
        try {
            return new ResponseEntity<>(agentService.deleteAgent(id), HttpStatus.OK);

        } catch (AgentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
