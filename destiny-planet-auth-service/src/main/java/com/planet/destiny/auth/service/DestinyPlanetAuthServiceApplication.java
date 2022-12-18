package com.planet.destiny.auth.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(value = "com.planet.destiny")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DestinyPlanetAuthServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(DestinyPlanetAuthServiceApplication.class, args);
    }
}
