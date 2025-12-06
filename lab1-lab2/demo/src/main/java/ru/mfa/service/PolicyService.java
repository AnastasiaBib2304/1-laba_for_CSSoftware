package ru.mfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfa.model.Customer;
import ru.mfa.model.Coverage;
import ru.mfa.model.Policy;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final Map<Long, Policy> policies = new HashMap<>();
    private long nextId = 1L;

    private final CustomerService customerService;
    private final CoverageService coverageService;

    public Policy addPolicy(Policy policy) {
        // Проверяем, существует ли клиент
        Customer customer = customerService.getCustomer(policy.getCustomerId());
        if (customer == null) {
            throw new IllegalArgumentException("Клиент с ID " + policy.getCustomerId() + " не найден");
        }

        // Проверяем, существуют ли покрытия
        for (Long coverageId : policy.getCoverageIds()) {
            Coverage coverage = coverageService.getCoverage(coverageId);
            if (coverage == null) {
                throw new IllegalArgumentException("Покрытие с ID " + coverageId + " не найдено");
            }
        }

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
            // Проверяем, существует ли клиент
            Customer customer = customerService.getCustomer(updatedPolicy.getCustomerId());
            if (customer == null) {
                throw new IllegalArgumentException("Клиент с ID " + updatedPolicy.getCustomerId() + " не найден");
            }

            // Проверяем, существуют ли покрытия
            for (Long coverageId : updatedPolicy.getCoverageIds()) {
                Coverage coverage = coverageService.getCoverage(coverageId);
                if (coverage == null) {
                    throw new IllegalArgumentException("Покрытие с ID " + coverageId + " не найдено");
                }
            }

            updatedPolicy.setId(id);
            policies.put(id, updatedPolicy);
            return updatedPolicy;
        }
        return null;
    }
}