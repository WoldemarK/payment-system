package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
@Data
@Builder
public class AddressDTO implements Serializable {
    private UUID id;
    private String city;
    private String state;
    private String address;
    private String zipCode;
    private CountriesDTO country;
}
