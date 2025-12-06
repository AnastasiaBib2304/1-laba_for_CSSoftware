package ru.mfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfa.model.Claim;
import ru.mfa.service.ClaimService;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<Claim> addClaim(@Valid @RequestBody Claim claim) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claimService.addClaim(claim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaim(@PathVariable long id) {
        var result = claimService.getClaim(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable long id, @Valid @RequestBody Claim updatedClaim) {
        var result = claimService.updateClaim(id, updatedClaim);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeClaim(@PathVariable long id) {
        claimService.removeClaim(id);
        return ResponseEntity.noContent().build();
    }
}