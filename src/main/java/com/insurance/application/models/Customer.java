package com.insurance.application.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\+?[0-9\\- ]{7,15}", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Postal code is required")
    @Size(min = 3, max = 10, message = "Postal code must be between 3 and 10 characters")
    private String postNumber;

    // Constructors
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String street,
                    String phone, String city, String postNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.phone = phone;
        this.city = city;
        this.postNumber = postNumber;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostNumber() { return postNumber; }
    public void setPostNumber(String postNumber) { this.postNumber = postNumber; }


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Insurance> insurances = new ArrayList<>();

    public List<Insurance> getInsurances() {
        return insurances;
    }
    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }
}