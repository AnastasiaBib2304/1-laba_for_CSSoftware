package ru.mfa.service;

import org.springframework.stereotype.Service;
import ru.mfa.model.Coverage;

import java.util.HashMap;
import java.util.Map;

@Service
public class CoverageService {
    private final Map<Long, Coverage> coverages = new HashMap<>();
    private long nextId = 1L;

    public Coverage addCoverage(Coverage coverage) {
        long id = nextId++;
        coverage.setId(id);
        coverages.put(id, coverage);
        return coverage;
    }

    public Coverage getCoverage(long id) {
        return coverages.getOrDefault(id, null);
    }

    public void removeCoverage(long id) {
        coverages.remove(id);
    }

    public Coverage updateCoverage(long id, Coverage updatedCoverage) {
        if (coverages.containsKey(id)) {
            updatedCoverage.setId(id);
            coverages.put(id, updatedCoverage);
            return updatedCoverage;
        }
        return null;
    }
}