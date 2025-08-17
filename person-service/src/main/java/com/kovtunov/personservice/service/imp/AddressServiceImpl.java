package com.kovtunov.personservice.service.imp;

import com.kovtunov.personservice.dto.AddressDTO;
import com.kovtunov.personservice.entity.Address;
import com.kovtunov.personservice.exception.AddressNotFoundException;
import com.kovtunov.personservice.repository.AddressRepository;
import com.kovtunov.personservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public Address addAddress(Address address) {
        log.info("In addAddress {}", address);
        if (address==null|| address.getAddress().trim().isEmpty()) {
            throw new AddressNotFoundException("address is null%s".formatted(address));
        }
        return addressRepository.save(address);
    }
//    public Address finById(UUID id) {
//        log.info("In finById {}", id);
//        if
//    }
}
