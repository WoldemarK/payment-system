package com.kovtunov.personservice.rest;

import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/country")
public class CountriesRestController {

    private final CountriesService countriesService;

    @PostMapping("/save")
    public ResponseEntity<Countries> save(@RequestBody Countries country) {
        Countries saved = countriesService.addCountry(country);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Countries>> getAll() {
        List<Countries> countries = countriesService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Countries> getById(@PathVariable Long id) {
        Countries country = countriesService.getCountryById(id);
        return ResponseEntity.ok(country);
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<Countries> getByName(@RequestParam String name) {
        Countries country = countriesService.getCountryByName(name);
        return ResponseEntity.ok(country);
    }

    @GetMapping("/search-by-names")
    public ResponseEntity<List<Countries>> search(@RequestParam String name) {
        List<Countries> countries = countriesService.searchCountries(name);
        return ResponseEntity.ok(countries);
    }
}
