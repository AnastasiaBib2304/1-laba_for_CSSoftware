package com.example.demo.controller;

import com.example.demo.model.Insurance;
import com.example.demo.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insurance")
@CrossOrigin(origins = "*")
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    //Создание через ppsot
    @PostMapping
    public ResponseEntity<?> createInsurance(@RequestBody Insurance insurance) {
        try {
            Insurance savedInsurance = insuranceRepository.save(insurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInsurance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating insurance: " + e.getMessage());
        }
    }

    // CREATE - Создание новой записи
    @GetMapping("/create")
    public ResponseEntity<?> createInsuranceViaGet(
            @RequestParam String policy,
            @RequestParam String customer,
            @RequestParam String claim,
            @RequestParam Double payment,
            @RequestParam String coverage) {

        try {
            Insurance insurance = new Insurance(policy, customer, claim, payment, coverage);
            Insurance savedInsurance = insuranceRepository.save(insurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInsurance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating insurance: " + e.getMessage());
        }
    }

    // READ ALL - Получение всех записей
    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurance() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        return ResponseEntity.ok(insuranceList);
    }

    // READ BY ID - Получение записи по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getInsuranceById(@PathVariable Long id) {
        Optional<Insurance> insurance = insuranceRepository.findById(id);
        if (insurance.isPresent()) {
            return ResponseEntity.ok(insurance.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Insurance with id " + id + " not found");
        }
    }
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInsurance(@PathVariable Long id, @RequestBody Insurance insuranceDetails) {
        try {
            Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
            if (optionalInsurance.isPresent()) {
                Insurance insurance = optionalInsurance.get();
                insurance.setPolicy(insuranceDetails.getPolicy());
                insurance.setCustomer(insuranceDetails.getCustomer());
                insurance.setClaim(insuranceDetails.getClaim());
                insurance.setPayment(insuranceDetails.getPayment());
                insurance.setCoverage(insuranceDetails.getCoverage());

                Insurance updatedInsurance = insuranceRepository.save(insurance);
                return ResponseEntity.ok(updatedInsurance);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Insurance with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating insurance: " + e.getMessage());
        }
    }

    // DELETE - Удаление записи
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInsurance(@PathVariable Long id) {
        try {
            if (insuranceRepository.existsById(id)) {
                insuranceRepository.deleteById(id);
                return ResponseEntity.ok().body("Insurance with id " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Insurance with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error deleting insurance: " + e.getMessage());
        }
    }
}