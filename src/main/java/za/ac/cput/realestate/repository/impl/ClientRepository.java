/* ClientRepository.java
   Repository for Client with Singleton pattern
   Author: Khonzinkosi Khumalo (230231802)
   Date: 23 March 2026
*/
package za.ac.cput.realestate.repository.impl;

import za.ac.cput.realestate.domain.Client;
import za.ac.cput.realestate.repository.IRepository;
import za.ac.cput.realestate.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository implements IRepository<Client, String> {

    private static ClientRepository repository = null;
    private Map<String, Client> clientDatabase;

    private ClientRepository() {
        clientDatabase = new HashMap<>();
    }

    public static ClientRepository getRepository() {
        if (repository == null) {
            repository = new ClientRepository();
        }
        return repository;
    }

    @Override
    public Client create(Client client) {
        if (Helper.isNullOrEmpty(client) || clientDatabase.containsKey(client.getClientId())) {
            return null;
        }
        clientDatabase.put(client.getClientId(), client);
        return client;
    }

    @Override
    public Client read(String clientId) {
        if (Helper.isNullOrEmpty(clientId)) {
            return null;
        }
        return clientDatabase.get(clientId);
    }

    @Override
    public Client update(Client client) {
        if (Helper.isNullOrEmpty(client) || !clientDatabase.containsKey(client.getClientId())) {
            return null;
        }
        clientDatabase.put(client.getClientId(), client);
        return client;
    }

    @Override
    public boolean delete(String clientId) {
        if (Helper.isNullOrEmpty(clientId)) {
            return false;
        }
        return clientDatabase.remove(clientId) != null;
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clientDatabase.values());
    }
}