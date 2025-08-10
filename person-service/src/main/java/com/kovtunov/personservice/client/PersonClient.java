package com.kovtunov.personservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient
        (
                name = "inventory-service",
                url = "http://inventory:8080/api",
                configuration = FeignConfig.class
        )
public interface PersonClient {
}
