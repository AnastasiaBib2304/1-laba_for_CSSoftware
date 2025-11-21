package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy")
    private String policy;

    @Column(name = "customer")
    private String customer;

    @Column(name = "claim")
    private String claim;

    @Column(name = "payment")
    private Double payment;

    @Column(name = "coverage")
    private String coverage;


    public Insurance() {}

    public Insurance(String policy, String customer, String claim, Double payment, String coverage) {
        this.policy = policy;
        this.customer = customer;
        this.claim = claim;
        this.payment = payment;
        this.coverage = coverage;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPolicy() { return policy; }
    public void setPolicy(String policy) { this.policy = policy; }

    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }

    public String getClaim() { return claim; }
    public void setClaim(String claim) { this.claim = claim; }

    public Double getPayment() { return payment; }
    public void setPayment(Double payment) { this.payment = payment; }

    public String getCoverage() { return coverage; }
    public void setCoverage(String coverage) { this.coverage = coverage; }
}