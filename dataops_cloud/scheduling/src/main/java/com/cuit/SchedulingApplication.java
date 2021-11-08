package com.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dailinfeng
 */
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan(basePackages = {"com.cuit.common"})
@ComponentScan(basePackages = {"com.cuit.scheduling"})
@ComponentScan(basePackages = {"com.cuit.task_handle"})
@ComponentScan(basePackages = {"com.cuit.rpc"})
@SpringBootApplication
public class SchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingApplication.class, args);
    }

}
