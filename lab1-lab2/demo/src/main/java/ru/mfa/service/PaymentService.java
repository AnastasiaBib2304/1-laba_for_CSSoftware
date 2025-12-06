package ru.mfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfa.model.Payment;
import ru.mfa.model.Policy;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final Map<Long, Payment> payments = new HashMap<>();
    private long nextId = 1L;

    private final PolicyService policyService;

    public Payment addPayment(Payment payment) {
        // Проверяем, существует ли полис
        Policy policy = policyService.getPolicy(payment.getPolicyId());
        if (policy == null) {
            throw new IllegalArgumentException("Полис с ID " + payment.getPolicyId() + " не найден");
        }

        long id = nextId++;
        payment.setId(id);
        payments.put(id, payment);
        return payment;
    }

    public Payment getPayment(long id) {
        return payments.getOrDefault(id, null);
    }

    public void removePayment(long id) {
        payments.remove(id);
    }

    public Payment updatePayment(long id, Payment updatedPayment) {
        if (payments.containsKey(id)) {
            // Проверяем, существует ли полис
            Policy policy = policyService.getPolicy(updatedPayment.getPolicyId());
            if (policy == null) {
                throw new IllegalArgumentException("Полис с ID " + updatedPayment.getPolicyId() + " не найден");
            }

            updatedPayment.setId(id);
            payments.put(id, updatedPayment);
            return updatedPayment;
        }
        return null;
    }
}