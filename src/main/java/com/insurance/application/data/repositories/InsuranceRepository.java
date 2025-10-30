package com.insurance.application.data.repositories;

import com.insurance.application.models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByCustomerId(Long customerId);
}
