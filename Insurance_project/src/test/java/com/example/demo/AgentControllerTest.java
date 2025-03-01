package com.example.demo;

import com.example.controller.AgentController;
import com.example.model.Agent;
import com.example.service.AgentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgentController.class)
public class AgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgentService agentService;

    @Autowired
    private ObjectMapper objectMapper;
    
    

    @Test
    public void testAddAgent() throws Exception {
        Agent agent = new Agent();
        agent.setAgentId(1234);
        agent.setName("John Doe");
        agent.setContactInfo("123456789");
        agent.setAssignedPolicies("LifeTime");

        when(agentService.addAgent(agent)).thenReturn(agent);

        mockMvc.perform(post("/agent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.agentId").value(1234));
    }
    
    @Test
    public void testFindAgentById() throws Exception {
        Agent agent = new Agent();
        agent.setAgentId(1234);
        agent.setName("John Doe");
        agent.setContactInfo("123456789");
        agent.setAssignedPolicies("LifeTime");

        when(agentService.findAgentbyId(1234)).thenReturn(agent);

        mockMvc.perform(get("/agent/1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.agentId").value(1234))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
    @Test
    public void testGetAllInsurance() throws Exception {
        Agent agent1 = new Agent();
        agent1.setAgentId(1);
        agent1.setName("Agent1");
        agent1.setContactInfo("12345678");
        agent1.setAssignedPolicies("Policy1");

        Agent agent2 = new Agent();
        agent2.setAgentId(2);
        agent2.setName("Agent2");
        agent2.setContactInfo("87654321");
        agent2.setAssignedPolicies("Policy2");

        List<Agent> agents = Arrays.asList(agent1, agent2);

        when(agentService.getAllAgent()).thenReturn(agents);

        mockMvc.perform(get("/agent")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].agentId").value(1))
                .andExpect(jsonPath("$[0].name").value("Agent1"))
                .andExpect(jsonPath("$[0].contactInfo").value("12345678"))
                .andExpect(jsonPath("$[0].assignedPolicies").value("Policy1"))
                .andExpect(jsonPath("$[1].agentId").value(2))
                .andExpect(jsonPath("$[1].name").value("Agent2"))
                .andExpect(jsonPath("$[1].contactInfo").value("87654321"))
                .andExpect(jsonPath("$[1].assignedPolicies").value("Policy2"));
    }
    
    @Test
    public void testUpdateAgent() throws Exception {
        Agent agent = new Agent();
        agent.setAgentId(1234);
        agent.setName("John Doe");
        agent.setContactInfo("123456789");
        agent.setAssignedPolicies("LifeTime");

        when(agentService.updateInsurance(agent)).thenReturn(agent);

        mockMvc.perform(put("/agent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(agent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.agentId").value(1234))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.contactInfo").value("123456789"))
                .andExpect(jsonPath("$.assignedPolicies").value("LifeTime"));
    }
    
    @Test
    public void testDeleteAgent() throws Exception {
        Agent agent = new Agent();
        agent.setAgentId(1234);
        agent.setName("John Doe");
        agent.setContactInfo("123456789");
        agent.setAssignedPolicies("LifeTime");

        when(agentService.deleteAgent(1234)).thenReturn(agent);

        mockMvc.perform(delete("/agent/1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.agentId").value(1234))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.contactInfo").value("123456789"))
                .andExpect(jsonPath("$.assignedPolicies").value("LifeTime"));
    }
    
    }

