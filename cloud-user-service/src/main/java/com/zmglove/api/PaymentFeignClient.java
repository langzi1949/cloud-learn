package com.zmglove.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-payment-service")
public interface PaymentFeignClient {

    @GetMapping("/payment/getAll")
    String getAll();
}
