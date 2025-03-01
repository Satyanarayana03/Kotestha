package com.example.demo;

import com.example.model.Agent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AgentTest {

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAgentEntity() {
		Agent agent = new Agent();
		agent.setAgentId(1234);
		agent.setName("Satish");
		agent.setContactInfo("123456789");
		agent.setAssignedPolicies("LifeTime");

		Assertions.assertEquals(1234, agent.getAgentId());
		Assertions.assertEquals("Satish", agent.getName());
		Assertions.assertEquals("123456789", agent.getContactInfo());
		Assertions.assertEquals("LifeTime", agent.getAssignedPolicies());
	}
}
