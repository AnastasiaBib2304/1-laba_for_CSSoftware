package ru.mfa.service;

import org.springframework.stereotype.Service;
import ru.mfa.model.Payment;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private final Map<Long, Payment> payments = new HashMap<>();
    private long nextId = 1L;

    public Payment addPayment(Payment payment) {
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
            updatedPayment.setId(id);
            payments.put(id, updatedPayment);
            return updatedPayment;
        }
        return null;
    }
}