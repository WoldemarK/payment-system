package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class IndividualsDTO {
    private UUID id;
    private String status;
    private String phoneNumber;
    private String passportNumber;
    private UserDTO user;

}
