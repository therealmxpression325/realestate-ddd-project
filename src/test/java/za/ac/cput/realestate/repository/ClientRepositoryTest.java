/* ClientRepositoryTest.java
   Test class for ClientRepository
   Author: Khonzinkosi Khumalo (230231802)
   Date: 25 March 2026
*/
package za.ac.cput.realestate.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.realestate.domain.Client;
import za.ac.cput.realestate.factory.ClientFactory;
import za.ac.cput.realestate.repository.impl.ClientRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientRepositoryTest {

    private static ClientRepository repository;
    private static Client client1;
    private static Client client2;

    @BeforeAll
    static void setup() {
        repository = ClientRepository.getRepository();
        client1 = ClientFactory.createClient(
                "C001", "Alice", "Johnson", "0845551234",
                "alice.j@email.com", 500000.00, "House"
        );
        client2 = ClientFactory.createClient(
                "C002", "Bob", "Williams", "0856667890",
                "bob.w@email.com", 350000.00, "Apartment"
        );
    }

    @BeforeEach
    void clearRepository() {
        repository.getAll().clear();
    }

    @Test
    @Order(1)
    void testCreate() {
        Client created = repository.create(client1);
        assertNotNull(created);
        assertEquals(client1.getClientId(), created.getClientId());
    }

    @Test
    @Order(2)
    void testCreate_Duplicate() {
        repository.create(client1);
        Client duplicate = repository.create(client1);
        assertNull(duplicate);
    }

    @Test
    @Order(3)
    void testRead() {
        repository.create(client1);
        Client read = repository.read(client1.getClientId());
        assertNotNull(read);
        assertEquals(client1.getClientId(), read.getClientId());
    }

    @Test
    @Order(4)
    void testUpdate() {
        repository.create(client1);
        Client updated = new Client.Builder().copy(client1)
                .setBudget(550000.00)
                .build();
        Client result = repository.update(updated);
        assertNotNull(result);
        assertEquals(550000.00, result.getBudget());
    }

    @Test
    @Order(5)
    void testDelete() {
        repository.create(client1);
        boolean deleted = repository.delete(client1.getClientId());
        assertTrue(deleted);
    }

    @Test
    @Order(6)
    void testGetAll() {
        repository.create(client1);
        repository.create(client2);
        List<Client> clients = repository.getAll();
        assertEquals(2, clients.size());
    }
}
