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
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.cuit.common.rpc"})
public class TaskHeandleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskHeandleApplication.class, args);
    }

}
