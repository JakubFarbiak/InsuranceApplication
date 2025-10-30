package com.insurance.application.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Poistenie je povinné")
    private String type; // Poistenie majetku, Auto, Životné, ...

    @NotNull(message = "Čiastka je povinná")
    private Double amount;

    @NotBlank(message = "Predmet poistenia je povinný")
    private String subject;

    private String validFrom;
    private String validTo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Insurance() {}

    public Insurance(String type, Double amount, String subject, String validFrom, String validTo, Customer customer) {
        this.type = type;
        this.amount = amount;
        this.subject = subject;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.customer = customer;
    }

    // Getters & Setters
    // ...

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getValidFrom() { return validFrom; }
    public void setValidFrom(String validFrom) { this.validFrom = validFrom; }

    public String getValidTo() { return validTo; }
    public void setValidTo(String validTo) { this.validTo = validTo; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }


}
