package com.kovtunov.personservice.service;

import com.kovtunov.personservice.entity.Countries;

import java.util.List;

public interface CountriesService {

    Countries getCountryById(Long id);

    Countries getCountryByName(String name);

    List<Countries> getAllCountries();

    Countries addCountry(Countries countries);

    List<Countries> searchCountries(String name);
}
