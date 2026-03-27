/* ClientFactoryTest.java
   Test class for ClientFactory
   Author: Khonzinkosi Khumalo (230231802)
   Date: 15 March 2026
*/
package za.ac.cput.realestate.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.realestate.domain.Client;
import static org.junit.jupiter.api.Assertions.*;

class ClientFactoryTest {

    @Test
    void createClient_Success() {
        Client client = ClientFactory.createClient(
                "C001", "Alice", "Johnson", "0845551234",
                "alice.j@email.com", 500000.00, "House"
        );

        assertNotNull(client);
        assertEquals("C001", client.getClientId());
        assertEquals("Alice", client.getFirstName());
        assertEquals("Johnson", client.getLastName());
        assertEquals("0845551234", client.getPhone());
        assertEquals("alice.j@email.com", client.getEmail());
        assertEquals(500000.00, client.getBudget());
        assertEquals("House", client.getPreferredType());
    }

    @Test
    void createClient_WithGeneratedId() {
        Client client = ClientFactory.createClient(
                "Alice", "Johnson", "0845551234",
                "alice.j@email.com", 500000.00, "House"
        );

        assertNotNull(client);
        assertNotNull(client.getClientId());
    }

    @Test
    void createClient_NullFirstName_ReturnsNull() {
        Client client = ClientFactory.createClient(
                "C001", null, "Johnson", "0845551234",
                "alice.j@email.com", 500000.00, "House"
        );
        assertNull(client);
    }

    @Test
    void createClient_InvalidEmail_ReturnsNull() {
        Client client = ClientFactory.createClient(
                "C001", "Alice", "Johnson", "0845551234",
                "invalid-email", 500000.00, "House"
        );
        assertNull(client);
    }

    @Test
    void createClient_InvalidPhone_ReturnsNull() {
        Client client = ClientFactory.createClient(
                "C001", "Alice", "Johnson", "abc",
                "alice.j@email.com", 500000.00, "House"
        );
        assertNull(client);
    }

    @Test
    void createClient_ZeroBudget_ReturnsNull() {
        Client client = ClientFactory.createClient(
                "C001", "Alice", "Johnson", "0845551234",
                "alice.j@email.com", 0, "House"
        );
        assertNull(client);
    }
}
