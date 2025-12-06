package ru.mfa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mfa.model.Coverage;
import ru.mfa.service.CoverageService;

@RestController
@RequestMapping("/coverages")
@RequiredArgsConstructor
public class CoverageController {
    private final CoverageService coverageService;

    @PostMapping
    public ResponseEntity<Coverage> addCoverage(@Valid @RequestBody Coverage coverage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coverageService.addCoverage(coverage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coverage> getCoverage(@PathVariable long id) {
        var result = coverageService.getCoverage(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coverage> updateCoverage(@PathVariable long id, @Valid @RequestBody Coverage updatedCoverage) {
        var result = coverageService.updateCoverage(id, updatedCoverage);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCoverage(@PathVariable long id) {
        coverageService.removeCoverage(id);
        return ResponseEntity.noContent().build();
    }
}