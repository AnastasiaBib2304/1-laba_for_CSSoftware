package ru.mfa.service;

import org.springframework.stereotype.Service;
import ru.mfa.model.Claim;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClaimService {
    private final Map<Long, Claim> claims = new HashMap<>();
    private long nextId = 1L;

    public Claim addClaim(Claim claim) {
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
            updatedClaim.setId(id);
            claims.put(id, updatedClaim);
            return updatedClaim;
        }
        return null;
    }
}