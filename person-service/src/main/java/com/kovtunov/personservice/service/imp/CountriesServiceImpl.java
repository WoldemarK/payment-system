package com.kovtunov.personservice.service.imp;

import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.exception.CountriesNotFoundException;
import com.kovtunov.personservice.exception.UserNotFoundException;
import com.kovtunov.personservice.repository.CountriesRepository;
import com.kovtunov.personservice.service.CountriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {

    private final CountriesRepository countriesRepository;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Countries addCountry(Countries country) {
        log.info("In addCountry {}", country);

        if (Objects.isNull(country)) {
            throw new CountriesNotFoundException("Country cannot be null%s".formatted(country));
        }
        return countriesRepository.save(country);
    }

    @Override
    public Countries getCountryById(Long id) {
        log.info("In getCountryById {}", id);
        return countriesRepository.findById(id)
                .orElseThrow(() -> new CountriesNotFoundException("Country not found with id: %d".formatted(id)));
    }

    @Override
    public Countries getCountryByName(String name) {
        log.info("In getCountryByName {}", name);
        return countriesRepository.findByName(name)
                .orElseThrow(() -> new CountriesNotFoundException("Country not found with name: %s".formatted(name)));
    }

    @Override
    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public List<Countries> searchCountries(String name) {
        log.info("In searchCountries {}", name);
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty%s".formatted(name));
        }
        return countriesRepository.findByNameContainingIgnoreCase(name);
    }
}
