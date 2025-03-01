
package com.example.demo;

import com.example.exception.AgentException;
import com.example.model.Agent;
import com.example.dao.AgentRepository;
import com.example.service.AgentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgentServiceTest {

    @Mock
    private AgentRepository agentRepository;

    @InjectMocks
    private AgentService agentService;

    private Agent agent;

    @BeforeEach
    void setUp() {
        agent = new Agent();
        agent.setAgentId(1);
        agent.setName("John Doe");
        agent.setContactInfo("123456789");
        agent.setAssignedPolicies("LifeTime");
    }

    @Test
    void testAddAgent_Success() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.empty());
        when(agentRepository.save(agent)).thenReturn(agent);

        Agent savedAgent = agentService.addAgent(agent);

        verify(agentRepository).save(agent);
        assertEquals(agent, savedAgent);
    }

    @Test
    void testAddAgent_AgentAlreadyExists() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.of(agent));

        assertThrows(AgentException.class, () -> agentService.addAgent(agent));
    }
    
    @Test
    void testGetAllAgent_Success() {
        List<Agent> agents = List.of(agent);
        when(agentRepository.findAll()).thenReturn(agents);

        List<Agent> result = agentService.getAllAgent();

        assertEquals(agents, result);
    }

    @Test
    void testGetAllAgent_EmptyList() {
        when(agentRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(AgentException.class, () -> agentService.getAllAgent());
    }
    
    @Test
    void testFindAgentById_Success() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.of(agent));

        Agent foundAgent = agentService.findAgentbyId(agent.getAgentId());

        assertEquals(agent, foundAgent);
    }

    @Test
    void testFindAgentById_NotFound() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.empty());

        assertThrows(AgentException.class, () -> agentService.findAgentbyId(agent.getAgentId()));
    }
    @Test
    void testUpdateAgent_Success() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.of(agent));
        when(agentRepository.save(agent)).thenReturn(agent);

        Agent updatedAgent = agentService.updateInsurance(agent);

        verify(agentRepository).save(agent);
        assertEquals(agent, updatedAgent);
    }

    @Test
    void testUpdateAgent_NotFound() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.empty());

        assertThrows(AgentException.class, () -> agentService.updateInsurance(agent));
    }
    
    @Test
    void testDeleteAgent_Success() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.of(agent));

        Agent deletedAgent = agentService.deleteAgent(agent.getAgentId());

        verify(agentRepository).deleteById(agent.getAgentId());
        assertEquals(agent, deletedAgent);
    }

    @Test
    void testDeleteAgent_NotFound() {
        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.empty());

        assertThrows(AgentException.class, () -> agentService.deleteAgent(agent.getAgentId()));
    }

}


