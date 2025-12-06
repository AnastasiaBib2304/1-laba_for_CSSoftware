package ru.mfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfa.model.Policy;
import ru.mfa.service.PolicyService;

@RestController
@RequestMapping("/policies")
@RequiredArgsConstructor
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<Policy> addPolicy(@Valid @RequestBody Policy policy) {
        return ResponseEntity.status(HttpStatus.CREATED).body(policyService.addPolicy(policy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicy(@PathVariable long id) {
        var result = policyService.getPolicy(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable long id, @Valid @RequestBody Policy updatedPolicy) {
        var result = policyService.updatePolicy(id, updatedPolicy);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePolicy(@PathVariable long id) {
        policyService.removePolicy(id);
        return ResponseEntity.noContent().build();
    }
}