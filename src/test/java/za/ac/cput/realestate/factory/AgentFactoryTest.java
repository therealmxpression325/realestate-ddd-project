/* AgentFactoryTest.java
   Test class for AgentFactory
   Author: Sinoxolo Kobeni (230801846)
   Date: 24 March 2026
*/

package za.ac.cput.realestate.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.realestate.domain.Agent;

import static org.junit.jupiter.api.Assertions.*;

class AgentFactoryTest {

    @Test
    void createAgent_Success() {
        Agent agent = AgentFactory.createAgent(
                "A001", "John", "Doe",
                "1234567890", "john@doe.com", "LIC123", 0.05
        );

        assertNotNull(agent);
        assertEquals("A001", agent.getAgentId());
        assertEquals("John", agent.getFirstName());
        assertEquals("Doe", agent.getLastName());
        assertEquals("1234567890", agent.getPhone());
        assertEquals("john@doe.com", agent.getEmail());
        assertEquals("LIC123", agent.getLicenseNumber());
        assertEquals(0.05, agent.getCommissionRate());
    }

    @Test
    void createAgent_NullAgentId_ReturnsNull() {
        Agent agent = AgentFactory.createAgent(
                null, "John", "Doe",
                "1234567890", "john@doe.com", "LIC123", 0.05
        );
        assertNull(agent);
    }

    @Test
    void createAgent_EmptyFirstName_ReturnsNull() {
        Agent agent = AgentFactory.createAgent(
                "A001", "", "Doe",
                "1234567890", "john@doe.com", "LIC123", 0.05
        );
        assertNull(agent);
    }

    @Test
    void createAgent_NegativeCommission_ReturnsNull() {
        Agent agent = AgentFactory.createAgent(
                "A001", "John", "Doe",
                "1234567890", "john@doe.com", "LIC123", -0.05
        );
        assertNull(agent);
    }

    @Test
    void createAgent_ZeroCommission_ReturnsNull() {  // adjust if zero is allowed
        Agent agent = AgentFactory.createAgent(
                "A001", "John", "Doe",
                "1234567890", "john@doe.com", "LIC123", 0.0
        );
        assertNull(agent);
    }

    @Test
    void createAgent_NullPhone_ReturnsNull() {
        Agent agent = AgentFactory.createAgent(
                "A001", "John", "Doe",
                null, "john@doe.com", "LIC123", 0.05
        );
        assertNull(agent);
    }
}
