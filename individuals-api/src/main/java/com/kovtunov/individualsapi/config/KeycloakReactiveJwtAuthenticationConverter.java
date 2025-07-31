package com.kovtunov.individualsapi.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class KeycloakReactiveJwtAuthenticationConverter implements Converter<Jwt, Flux<GrantedAuthority>> {

    private final static String ROLE = "roles";

    @Override
    public Flux<GrantedAuthority> convert(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList(ROLE);
        if (roles == null || roles.isEmpty()) {
            return Flux.empty();
        }
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return Flux.fromIterable(authorities);
    }
}
