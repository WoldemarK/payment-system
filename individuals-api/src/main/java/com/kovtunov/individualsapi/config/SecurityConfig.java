package com.kovtunov.individualsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] authenticatedPaths =
            {
                    "/v1/auth/me",
                    "/v1/auth/refresh-token"
            };
    private static final String[] permitAllPaths =
            {
                    "/v1/auth/registration",
                    "/v1/auth/login"
            };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange
                        (
                                exchanges -> exchanges
                                        .pathMatchers(authenticatedPaths).authenticated()
                                        .pathMatchers(permitAllPaths).permitAll()
                                        .anyExchange().permitAll()
                        )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }

    private ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new ReactiveJwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakReactiveJwtAuthenticationConverter());
        return converter;
    }
}
