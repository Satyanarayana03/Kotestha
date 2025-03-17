package com.example.service;

import com.example.exception.AgentException;
import com.example.model.Agent;
import com.example.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    @Autowired
    AgentRepository agentRepository;

    public Agent addAgent(Agent agent) {
        if (agentRepository.findById(agent.getAgentId()).isPresent()) {
            throw new AgentException("Agent with id already present");
        }else {
            return agentRepository.save(agent);
        }
    }

    public Agent updateAgent(Agent agent) {
        if (agentRepository.findById(agent.getAgentId()).isEmpty()) {
            throw new AgentException("Insurance with id not found");
        }else {
            return agentRepository.save(agent);
        }
    }

    public List<Agent> getAllAgent() {
        if (agentRepository.findAll().isEmpty()) {
            throw new AgentException("Agent list is empty");
        }else {
            return agentRepository.findAll();
        }
    }

    public Agent findAgentbyId(int id) {
        if (agentRepository.findById(id).isEmpty()) {
            throw new AgentException("Agent with id not found");
        }else {
            return agentRepository.findById(id).get();
        }
    }

    public Agent deleteAgent(int id) {
        Optional<Agent> byId = agentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AgentException("id not present");
        } else {
            agentRepository.deleteById(id);
            return byId.get();
        }
    }
}
