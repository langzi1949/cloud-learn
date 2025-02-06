package com.zmglove.controller;

import com.zmglove.api.PaymentFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentFeignClient paymentFeignClient;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Bean
    @LoadBalanced
    public RestTemplate fetchRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }


    @GetMapping("/get/v1/{id}")
    public String getByIdV1(@PathVariable(value = "id") long id) {
        ServiceInstance instance = loadBalancerClient.choose("cloud-payment-service");
        String url = String.format("http://%s:%s/payment/getAll", instance.getHost(), instance.getPort());
        String msg = restTemplate.getForObject(url, String.class);
        return "V1 User[" + id + "]----" + msg;

    }

    @GetMapping("/get/{id}")
    public String getById(@PathVariable("id") long id) {
        String url = "http://cloud-payment-service/payment/getAll";
        String msg = restTemplate.getForObject(url, String.class);
        return "用户【" + id + "】---" + msg;
    }


    @GetMapping("/get-feign/{id}")
    public String getFeignById(@PathVariable("id") long id) {
        String msg = paymentFeignClient.getAll();
        return "用户【" + id + "】---" + msg;
    }
}
