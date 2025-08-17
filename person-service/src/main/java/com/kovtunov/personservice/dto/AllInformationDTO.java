package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AllInformationDTO implements Serializable {
    private UserDTO user;
    private AddressDTO address;
    private CountriesDTO countries;
    private IndividualsDTO individuals;

}
