package za.ac.cput.realestate.repository.impl;

import za.ac.cput.realestate.domain.Agent;
import za.ac.cput.realestate.repository.IRepository;
import za.ac.cput.realestate.util.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class AgentRepository implements IRepository<Agent, String> {

    private static AgentRepository repository = null;
    private Map<String, Agent> agentDatabase;

    private AgentRepository() {
        agentDatabase = new HashMap<>();
    }

    public static AgentRepository getRepository() {
        if (repository == null) {
            repository = new AgentRepository();
        }
        return repository;
    }

    @Override
    public Agent create(Agent agent) {
        if (Helper.isNullOrEmpty(agent) || agentDatabase.containsKey(agent.getAgentId())) {
            return null;
        }
        agentDatabase.put(agent.getAgentId(), agent);
        return agent;
    }

    @Override
    public Agent read(String agentId) {
        if (Helper.isNullOrEmpty(agentId)) {
            return null;
        }
        return agentDatabase.get(agentId);
    }

    @Override
    public Agent update(Agent agent) {
        if (Helper.isNullOrEmpty(agent) || !agentDatabase.containsKey(agent.getAgentId())) {
            return null;
        }
        agentDatabase.put(agent.getAgentId(), agent);
        return agent;
    }

    @Override
    public boolean delete(String agentId) {
        if (Helper.isNullOrEmpty(agentId)) {
            return false;
        }
        return agentDatabase.remove(agentId) != null;
    }

    @Override
    public List<Agent> getAll() {
        return new ArrayList<>(agentDatabase.values());
    }

    // Additional methods for agent-specific queries
    public List<Agent> getAgentsByFirstName(String firstName) {
        if (Helper.isNullOrEmpty(firstName)) {
            return new ArrayList<>();
        }
        return agentDatabase.values().stream()
                .filter(agent -> agent.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }

    public List<Agent> getAgentsByLastName(String lastName) {
        if (Helper.isNullOrEmpty(lastName)) {
            return new ArrayList<>();
        }
        return agentDatabase.values().stream()
                .filter(agent -> agent.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public Agent getAgentByLicenseNumber(String licenseNumber) {
        if (Helper.isNullOrEmpty(licenseNumber)) {
            return null;
        }
        return agentDatabase.values().stream()
                .filter(agent -> agent.getLicenseNumber().equals(licenseNumber))
                .findFirst()
                .orElse(null);
    }

    public Agent getAgentByEmail(String email) {
        if (Helper.isNullOrEmpty(email)) {
            return null;
        }
        return agentDatabase.values().stream()
                .filter(agent -> agent.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<Agent> getAgentsByCommissionRateRange(double minRate, double maxRate) {
        if (minRate < 0 || maxRate < minRate) {
            return new ArrayList<>();
        }
        return agentDatabase.values().stream()
                .filter(agent -> agent.getCommissionRate() >= minRate && agent.getCommissionRate() <= maxRate)
                .collect(Collectors.toList());
    }

    public void clear() {
    }
}
