package com.kovtunov.personservice.repository;

import com.kovtunov.personservice.entity.Countries;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {

    Optional<Countries> findById(Long id);

    Optional<Countries> findByName(String name);

    List<Countries> findByNameContainingIgnoreCase(String name);
}
