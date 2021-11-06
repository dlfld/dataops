package com.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dailinfeng
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan(basePackages = {"com.cuit.common"})
@ComponentScan(basePackages = {"com.cuit.api"})
public class FileManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManageApplication.class, args);
    }

}
