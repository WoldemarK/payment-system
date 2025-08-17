package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
@Data
@Builder
public class IndividualsDTO implements Serializable {
    private UUID id;
    private String status;
    private String phoneNumber;
    private String passportNumber;
    private UserDTO user;

}
