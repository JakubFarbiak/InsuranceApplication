package com.poistenci.app.data.repositories;


import com.poistenci.app.models.NovyPoistenecDTO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface novyPoistenecRepository extends JpaRepository<NovyPoistenecDTO, Long> {
}
