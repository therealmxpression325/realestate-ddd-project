
package za.ac.cput.realestate.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.realestate.domain.Agent;
import za.ac.cput.realestate.factory.AgentFactory;
import za.ac.cput.realestate.repository.impl.AgentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgentRepositoryTest {

    private AgentRepository repository;
    private Agent agent1;
    private Agent agent2;

    @BeforeEach
    void setUp() {
        repository = AgentRepository.getRepository();
        repository.clear();  // make sure you have a clear() method for testing
        agent1 = AgentFactory.createAgent(
                "A001", "John", "Doe",
                "1234567890", "john@example.com", "LIC123", 0.05
        );
        agent2 = AgentFactory.createAgent(
                "A002", "Jane", "Smith",
                "0987654321", "jane@example.com", "LIC456", 0.07
        );
    }

    @Test
    void create_Success() {
        Agent created = repository.create(agent1);
        assertNotNull(created);
        assertEquals(agent1, created);
        assertEquals(agent1, repository.read("A001"));
    }

    @Test
    void create_Duplicate_ReturnsNull() {
        repository.create(agent1);
        Agent duplicate = repository.create(agent1);
        assertNull(duplicate);
    }

    @Test
    void read_Existing_ReturnsAgent() {
        repository.create(agent1);
        Agent found = repository.read("A001");
        assertEquals(agent1, found);
    }

    @Test
    void read_NonExisting_ReturnsNull() {
        Agent found = repository.read("A999");
        assertNull(found);
    }

    @Test
    void read_NullId_ReturnsNull() {
        Agent found = repository.read(null);
        assertNull(found);
    }

    @Test
    void update_Existing_ReturnsUpdated() {
        repository.create(agent1);
        Agent updated = new Agent.Builder()
                .copy(agent1)
                .setPhone("1111111111")
                .setCommissionRate(0.10)
                .build();
        Agent result = repository.update(updated);
        assertNotNull(result);
        assertEquals("1111111111", result.getPhone());
        assertEquals(0.10, result.getCommissionRate());
        assertEquals(updated, repository.read("A001"));
    }

    @Test
    void update_NonExisting_ReturnsNull() {
        Agent result = repository.update(agent1);
        assertNull(result);
    }

    @Test
    void delete_Existing_ReturnsTrue() {
        repository.create(agent1);
        boolean deleted = repository.delete("A001");
        assertTrue(deleted);
        assertNull(repository.read("A001"));
    }

    @Test
    void delete_NonExisting_ReturnsFalse() {
        boolean deleted = repository.delete("A999");
        assertFalse(deleted);
    }

    @Test
    void getAll_ReturnsAll() {
        repository.create(agent1);
        repository.create(agent2);
        List<Agent> all = repository.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(agent1));
        assertTrue(all.contains(agent2));
    }

    @Test
    void getAgentsByFirstName_ReturnsMatching() {
        repository.create(agent1);
        repository.create(agent2);
        List<Agent> result = repository.getAgentsByFirstName("John");
        assertEquals(1, result.size());
        assertEquals(agent1, result.get(0));
    }

    @Test
    void getAgentByLicenseNumber_ReturnsCorrect() {
        repository.create(agent1);
        repository.create(agent2);
        Agent found = repository.getAgentByLicenseNumber("LIC456");
        assertEquals(agent2, found);
    }

    @Test
    void getAgentByEmail_ReturnsCorrect() {
        repository.create(agent1);
        repository.create(agent2);
        Agent found = repository.getAgentByEmail("john@example.com");
        assertEquals(agent1, found);
    }
}
