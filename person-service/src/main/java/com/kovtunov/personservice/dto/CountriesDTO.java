package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountriesDTO {
    private Long id;
    private String name;
    private String alpha2;
    private String alpha3;
    private String status;

}
