package com.example.service;


import com.example.dao.PolicyRepository;
import com.example.exception.AgentException;
import com.example.model.Agent;
import com.example.dao.AgentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AgentService {
	
	@Autowired
	AgentRepository agentRepository;
	
	public Agent addAgent(Agent agent) {
		if (agentRepository.findById(agent.getAgentId()).isPresent()) {
			log.error("insurance with id already present");
			throw new AgentException("Agent with id already present");
		}else {
			log.info("insurance object saved "+agent);

			return agentRepository.save(agent);
	}
	}
	
	public List<Agent> getAllAgent() {
		log.info("getAllInsurance ");
		if (agentRepository.findAll().isEmpty()) {
			log.error("is empty");
			throw new AgentException("Agent list is empty");
		}else {
			log.info("found all "+agentRepository.findAll());
			return agentRepository.findAll();
	}
	}
	
	public Agent findAgentbyId(int id) {
		log.debug("findAgentById method");
		if (agentRepository.findById(id).isEmpty()) {
			log.error("agent with id not found");
			throw new AgentException("Agent with id not found");
		}else {
			log.info("found by Id "+id+" value "+agentRepository.findById(id).get());
			return agentRepository.findById(id).get();
	}
	}
	
	public Agent updateInsurance(Agent agent) {
		log.info("updateAgent called");
		if (agentRepository.findById(agent.getAgentId()).isEmpty()) {
			log.error("Agent with id not found");
			throw new AgentException("Insurance with id not found");
		}else {
			log.info("agent updated "+agent);
			return agentRepository.save(agent);
	}
	}
	public Agent deleteAgent(int id) {
		log.info("delete agent called ");
		if (agentRepository.findById(id).isEmpty()) {
			log.error("agent with id not found");
			throw new AgentException("Agent with id not found");
		}else {
			Agent agent=agentRepository.findById(id).get();
			log.info("deleted by id "+id+" value "+agent);
			agentRepository.deleteById(id);
			return agent;
		}
			
	}
	
	

}
