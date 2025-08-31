package com.kovtunov.personservice.service.imp;

import com.kovtunov.personservice.entity.Address;
import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.entity.Individuals;
import com.kovtunov.personservice.entity.User;
import com.kovtunov.personservice.exception.UserNotFoundException;
import com.kovtunov.personservice.exception.UserWithDuplicateEmailException;
import com.kovtunov.personservice.repository.AddressRepository;
import com.kovtunov.personservice.repository.IndividualsRepository;
import com.kovtunov.personservice.repository.UserRepository;
import com.kovtunov.personservice.service.CountriesService;
import com.kovtunov.personservice.service.UserService;
import com.kovtynov.person_service.api.dto.AddressDTO;
import com.kovtynov.person_service.api.dto.AllInformationDTO;
import com.kovtynov.person_service.api.dto.CountriesDTO;
import com.kovtynov.person_service.api.dto.IndividualsDTO;
import com.kovtynov.person_service.api.dto.UserDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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
    public AllInformationDTO createFullUser(AllInformationDTO allInformation) {
        log.info("createFullUser {}", allInformation);

        if (Objects.isNull(allInformation)) {
            throw new EntityNotFoundException("AllInformationDTO not found%s".formatted(allInformation));
        }
        Countries countries = createCountry(allInformation.getCountries());
        Address address = createAddress(allInformation.getAddress(), countries);
        User user = createUser(allInformation.getUser(), address);
        createIndividuals(allInformation.getIndividuals(), user);

        return allInformation;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public User createOnlyUser(User user) {
        log.info("createOnlyUser {}", user);

        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("User not found %s".formatted(user));
        }
        User duplicateCandidate = userRepository.findByEmail(user.getEmail());

        if (Objects.nonNull(duplicateCandidate)) {
            throw new UserWithDuplicateEmailException("User with email already exists%s".formatted(user));
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUUID(UUID uuid) {
        log.info("getUserByUUID {}", uuid);
        return userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User with UUID %s not found".formatted(uuid)));
    }

    @Override
    public User findByEmail(String email) {
        log.info("findByEmail {}", email);
        User obtainedUser =  userRepository.findByEmail(email);
        if (Objects.isNull(obtainedUser)) {
            throw new UserNotFoundException("User with email %s not found".formatted(email));
        }
        return userRepository.findByEmail(email);
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
                                .filled(userDto.getFilled())
                                .lastName(userDto.getLastName())
                                .firstName(userDto.getFirstName())
                                .secretKey(userDto.getSecretKey())
                                .email(userDto.getEmail())
                                .addressId(address)
                                .build()
                );
    }

    private void createIndividuals(IndividualsDTO individualsDto, User user) {
        individualsRepository.save
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
