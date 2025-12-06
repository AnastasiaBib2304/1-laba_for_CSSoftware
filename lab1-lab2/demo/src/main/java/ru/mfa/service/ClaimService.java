package ru.mfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfa.model.Claim;
import ru.mfa.model.Policy;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final Map<Long, Claim> claims = new HashMap<>();
    private long nextId = 1L;

    private final PolicyService policyService;

    public Claim addClaim(Claim claim) {
        // Проверяем, существует ли полис
        Policy policy = policyService.getPolicy(claim.getPolicyId());
        if (policy == null) {
            throw new IllegalArgumentException("Полис с ID " + claim.getPolicyId() + " не найден");
        }

        long id = nextId++;
        claim.setId(id);
        claims.put(id, claim);
        return claim;
    }

    public Claim getClaim(long id) {
        return claims.getOrDefault(id, null);
    }

    public void removeClaim(long id) {
        claims.remove(id);
    }

    public Claim updateClaim(long id, Claim updatedClaim) {
        if (claims.containsKey(id)) {
            // Проверяем, существует ли полис
            Policy policy = policyService.getPolicy(updatedClaim.getPolicyId());
            if (policy == null) {
                throw new IllegalArgumentException("Полис с ID " + updatedClaim.getPolicyId() + " не найден");
            }

            updatedClaim.setId(id);
            claims.put(id, updatedClaim);
            return updatedClaim;
        }
        return null;
    }
}