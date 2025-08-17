package com.kovtunov.personservice.service.imp;

import com.kovtunov.personservice.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountriesService countriesService;
    private final AddressRepository addressRepository;
    private final IndividualsRepository individualsRepository;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void createFullUser(AllInformationDTO allInformationDTO) {
        log.info("createFullUser {}", allInformationDTO);
        Countries countries = createCountry(allInformationDTO.getCountries());
        Address address = createAddress(allInformationDTO.getAddress(), countries);
        User user = createUser(allInformationDTO.getUser(), address);
        createIndividuals(allInformationDTO.getIndividuals(), user);


    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUUID(UUID uuid) {
        log.info("getUserByUUID {}", uuid);
        return userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User with UUID " + uuid + " not found"));
    }

    private Countries createCountry(CountriesDTO countryDto) {
        return countriesService.addCountry(Countries.builder()
                .name(countryDto.getName())
                .alpha2(countryDto.getAlpha2())
                .alpha3(countryDto.getAlpha3())
                .status(countryDto.getStatus())
                .build());
    }

    private Address createAddress(AddressDTO addressDto, Countries country) {
        return addressRepository.save
                (
                        Address.builder()
                                .city(addressDto.getCity())
                                .state(addressDto.getState())
                                .address(addressDto.getAddress())
                                .zipCode(addressDto.getZipCode())
                                .countryId(country)
                                .build()
                );
    }

    private User createUser(UserDTO userDto, Address address) {
        return userRepository.save
                (
                        User.builder()
                                .filled(userDto.isFilled())
                                .lastName(userDto.getLastName())
                                .firstName(userDto.getFirstName())
                                .secretKey(userDto.getSecretKey())
                                .email(userDto.getEmail())
                                .addressId(address)
                                .build()
                );
    }

    private Individuals createIndividuals(IndividualsDTO individualsDto, User user) {
        return individualsRepository.save
                (
                        Individuals.builder()
                                .status(individualsDto.getStatus())
                                .phoneNumber(individualsDto.getPhoneNumber())
                                .passportNumber(individualsDto.getPassportNumber())
                                .userId(user)
                                .build()
                );
    }


    private final UserRepository userRepository;
    private final CountriesService countriesService;
    private final AddressRepository addressRepository;
    private final IndividualsRepository individualsRepository;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void createFullUser(AllInformationDTO allInformationDTO) {
        log.info("createFullUser {}", allInformationDTO);
        Countries countries = createCountry(allInformationDTO.getCountries());
        Address address = createAddress(allInformationDTO.getAddress(), countries);
        User user = createUser(allInformationDTO.getUser(), address);
        createIndividuals(allInformationDTO.getIndividuals(), user);


    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUUID(UUID uuid) {
        log.info("getUserByUUID {}", uuid);
        return userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User with UUID " + uuid + " not found"));
    }

    private Countries createCountry(CountriesDTO countryDto) {
        return countriesService.addCountry(Countries.builder()
                .name(countryDto.getName())
                .alpha2(countryDto.getAlpha2())
                .alpha3(countryDto.getAlpha3())
                .status(countryDto.getStatus())
                .build());
    }

    private Address createAddress(AddressDTO addressDto, Countries country) {
        return addressRepository.save
                (
                        Address.builder()
                                .city(addressDto.getCity())
                                .state(addressDto.getState())
                                .address(addressDto.getAddress())
                                .zipCode(addressDto.getZipCode())
                                .countryId(country)
                                .build()
                );
    }

    private User createUser(UserDTO userDto, Address address) {
        return userRepository.save
                (
                        User.builder()
                                .filled(userDto.isFilled())
                                .lastName(userDto.getLastName())
                                .firstName(userDto.getFirstName())
                                .secretKey(userDto.getSecretKey())
                                .email(userDto.getEmail())
                                .addressId(address)
                                .build()
                );
    }

    private Individuals createIndividuals(IndividualsDTO individualsDto, User user) {
        return individualsRepository.save
                (
                        Individuals.builder()
                                .status(individualsDto.getStatus())
                                .phoneNumber(individualsDto.getPhoneNumber())
                                .passportNumber(individualsDto.getPassportNumber())
                                .userId(user)
                                .build()
                );
    }
}
