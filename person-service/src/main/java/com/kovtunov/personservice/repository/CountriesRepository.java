package com.kovtunov.personservice.repository;

import com.kovtunov.personservice.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {
}
