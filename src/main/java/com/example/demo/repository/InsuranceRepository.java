package com.example.demo.repository;

import com.example.demo.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByCustomer(String customer);
    List<Insurance> findByPolicy(String policy);
}