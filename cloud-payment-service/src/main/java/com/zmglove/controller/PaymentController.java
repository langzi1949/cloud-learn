package com.zmglove.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private int port;


    @GetMapping("/getAll")
    public String getAll() {
        return "Payment All list" + "来自 支付服务: 【" + port + "】";
    }
}
