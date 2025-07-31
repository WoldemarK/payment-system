package com.kovtunov.individualsapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRepresentationDto {

    private String email;
    private boolean enabled = true;
    private boolean emailVerified = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Credentials credentials;

    public UserRepresentationDto(String email, String password) {
        this.email = email;
        this.credentials = new Credentials("password", false, password);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Credentials {
        private String type;
        private boolean temporary;
        private String value;
    }
}
