package com.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author dailinfeng
 */
@EnableDiscoveryClient
@SpringBootApplication
//@ComponentScan(basePackages = {"com.cuit.common"})

@ComponentScan(excludeFilters =
        {
                @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.cuit.common.advice.*")
        })
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
