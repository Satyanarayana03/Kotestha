package com.example.controller;

import com.example.exception.AgentException;
import com.example.model.Agent;
import com.example.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class AgentController {
	
	@Autowired
	AgentService agentService;
	
	@PostMapping("/agent")
	public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
		log.info("addAgent");
		try {
			log.info("Agent saved");
			return new ResponseEntity<>(agentService.addAgent(agent),HttpStatus.CREATED);
		} catch (AgentException e) {
			log.error("add Agent error "+e.getMessage()+" status "+HttpStatus.NOT_FOUND);
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/agent")
	public  ResponseEntity<List<Agent>> getAllInsurance() {
		log.info("getAllInsurance");
		try {
			log.info("found "+agentService.getAllAgent()+" status "+ HttpStatus.OK);
			return new ResponseEntity<>(agentService.getAllAgent(),HttpStatus.OK);
		} catch (AgentException e) {
			log.error("Error "+e.getMessage()+" status "+HttpStatus.NOT_FOUND);
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/agent/{id}")
	public ResponseEntity<Agent> findAgentbyId(@PathVariable int id) {
		log.info("findInsuranceById");
		try {
			log.info("found "+agentService.findAgentbyId(id)+" status "+HttpStatus.FOUND);
			return new ResponseEntity<>(agentService.findAgentbyId(id),HttpStatus.FOUND);
		} catch (AgentException e) {
			log.error("error "+e.getMessage()+" status "+HttpStatus.NOT_FOUND);
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/agent")
	public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) {
		log.info("UpdateAgent");
		try {
			Agent updateAgent=agentService.updateInsurance(agent);
			log.info("updated "+updateAgent+" status "+HttpStatus.OK);
			return new ResponseEntity<>(updateAgent,HttpStatus.OK);
		} catch (AgentException e) {
			log.error(e.getMessage()+" status "+HttpStatus.NOT_FOUND);
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/agent/{id}")
	public ResponseEntity<Agent> deleteAgent(@PathVariable int id) {
		log.info("delete agent");
		try {
			Agent deleteAgent=agentService.deleteAgent(id);
			log.info("deleted with id "+id+" value "+deleteAgent+" status "+HttpStatus.OK);
			return new ResponseEntity<>(deleteAgent,HttpStatus.OK);
		} catch (AgentException e) {
			log.error(e.getMessage()+" status "+HttpStatus.NOT_FOUND);
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
			
	}

}
