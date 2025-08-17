package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllInformationDTO {
    private UserDTO user;
    private AddressDTO address;
    private CountriesDTO countries;
    private IndividualsDTO individuals;

}
