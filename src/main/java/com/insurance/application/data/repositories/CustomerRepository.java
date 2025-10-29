package com.insurance.application.data.repositories;


import com.insurance.application.models.Customer;  // renamed entity
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}