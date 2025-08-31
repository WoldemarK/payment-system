package com.kovtunov.personservice.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "personApiClient", url = "${feignClientUrl}", configuration = FeignConfig.class)
public interface ClientApi {
}
