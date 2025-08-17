package com.kovtunov.personservice.rest;

import com.kovtunov.personservice.dto.CountriesDTO;
import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.mapper.CustomMapper;
import com.kovtunov.personservice.service.CountriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/country")
public class CountriesRestController {

    private final CountriesService countriesService;
    private final CustomMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<CountriesDTO> save(@RequestBody Countries country) {
        log.info("In save country {}", country);
        CountriesDTO saved = mapper.toDtoCountries(countriesService.addCountry(country));
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CountriesDTO>> getAll() {
        log.info("In getAllCountries");
        List<Countries> countries = countriesService.getAllCountries();
        return ResponseEntity.ok(countries.stream()
                .map(mapper::toDtoCountries)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountriesDTO> getById(@PathVariable Long id) {
        log.info("In getCountryById {}", id);
        Countries country = countriesService.getCountryById(id);
        return ResponseEntity.ok(mapper.toDtoCountries(country));
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<CountriesDTO> getByName(@RequestParam String name) {
        log.info("In getCountryByName {}", name);
        Countries country = countriesService.getCountryByName(name);
        return ResponseEntity.ok(mapper.toDtoCountries(country));
    }

    @GetMapping("/search-by-names")
    public ResponseEntity<List<CountriesDTO>> search(@RequestParam String name) {
        log.info("In searchCountryByName {}", name);
        List<Countries> countries = countriesService.searchCountries(name);
        return ResponseEntity.ok(countries.stream()
                .map(mapper::toDtoCountries)
                .collect(Collectors.toList()));
    }
}
