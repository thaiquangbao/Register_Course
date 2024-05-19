package com.iuh.springgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringgatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringgatewayServiceApplication.class, args);
    }

}
