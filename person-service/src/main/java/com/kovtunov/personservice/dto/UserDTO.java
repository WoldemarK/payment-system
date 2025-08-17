package com.kovtunov.personservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
@Data
@Builder
public class UserDTO implements Serializable {
    private UUID id;
    private boolean filled;
    private String firstName;
    private String lastName;
    private String email;
    private String secretKey;
    private AddressDTO address;
}
