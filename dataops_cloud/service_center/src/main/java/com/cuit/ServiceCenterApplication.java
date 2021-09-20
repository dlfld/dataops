package com.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan(basePackages = {"com.cuit.common"})
@SpringBootApplication
@EnableAsync
public class ServiceCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCenterApplication.class, args);
    }

}
