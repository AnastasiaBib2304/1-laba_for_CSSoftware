package ru.mfa.service;

import org.springframework.stereotype.Service;
import ru.mfa.model.Policy;

import java.util.HashMap;
import java.util.Map;

@Service
public class PolicyService {
    private final Map<Long, Policy> policies = new HashMap<>();
    private long nextId = 1L;

    public Policy addPolicy(Policy policy) {
        long id = nextId++;
        policy.setId(id);
        policies.put(id, policy);
        return policy;
    }

    public Policy getPolicy(long id) {
        return policies.getOrDefault(id, null);
    }

    public void removePolicy(long id) {
        policies.remove(id);
    }

    public Policy updatePolicy(long id, Policy updatedPolicy) {
        if (policies.containsKey(id)) {
            updatedPolicy.setId(id);
            policies.put(id, updatedPolicy);
            return updatedPolicy;
        }
        return null;
    }
}