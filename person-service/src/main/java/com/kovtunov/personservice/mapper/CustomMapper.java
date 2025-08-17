package com.kovtunov.personservice.mapper;

import com.kovtunov.personservice.dto.AddressDTO;
import com.kovtunov.personservice.dto.CountriesDTO;
import com.kovtunov.personservice.dto.IndividualsDTO;
import com.kovtunov.personservice.dto.UserDTO;
import com.kovtunov.personservice.entity.Address;
import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.entity.Individuals;
import com.kovtunov.personservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CustomMapper {

    public UserDTO toDtoUser(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .filled(user.isFilled())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .secretKey(user.getSecretKey())
               .address(toDtoAddress(user.getAddressId()))
                .build();
    }

    public AddressDTO toDtoAddress(Address address) {
        if (address == null) return null;
        return AddressDTO.builder()
                .id(address.getId())
                .city(address.getCity())
                .state(address.getState())
                .address(address.getAddress())
                .zipCode(address.getZipCode())
                .country(toDtoCountries(address.getCountryId()))
                .build();
    }

    public CountriesDTO toDtoCountries(Countries country) {
        if (country == null) return null;
        return CountriesDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .alpha2(country.getAlpha2())
                .alpha3(country.getAlpha3())
                .status(country.getStatus())
                .build();
    }

    public IndividualsDTO toDtoIndividuals(Individuals individuals) {
        return IndividualsDTO.builder()
                .status(individuals.getStatus())
                .passportNumber(individuals.getPassportNumber())
                .passportNumber(individuals.getPassportNumber())
                .user(toDtoUser(individuals.getUserId()))
                .build();
    }
}
