package com.kovtunov.personservice.service.imp;

import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.exception.CountriesNotFoundException;
import com.kovtunov.personservice.repository.CountriesRepository;
import com.kovtunov.personservice.service.CountriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {

    private final CountriesRepository countriesRepository;

    @Transactional(rollbackFor = SQLException.class)
    public Countries addCountry(Countries country) {
        if (country == null) {
            throw new IllegalArgumentException("Country cannot be null");
        }
        if (country.getName() == null || country.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be null or empty");
        }
        return countriesRepository.save(country);
    }

    @Override
    public Countries getCountryById(Long id) {
        return countriesRepository.findById(id)
                .orElseThrow(() -> new CountriesNotFoundException("Country not found with id: " + id));
    }

    @Override
    public Countries getCountryByName(String name) {
        return countriesRepository.findByName(name)
                .orElseThrow(() -> new CountriesNotFoundException("Country not found with name: " + name));
    }

    @Override
    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public List<Countries> searchCountries(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }
        return countriesRepository.findByNameContainingIgnoreCase(name);
    }
}
