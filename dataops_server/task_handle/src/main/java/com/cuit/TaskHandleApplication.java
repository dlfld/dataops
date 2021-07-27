package com.cuit;

import com.cuit.common.pojo.Option;
import com.cuit.common.pojo.bo.ParamsBody;
import com.cuit.common.pojo.bo.ParamsBody2;
import com.cuit.common.rpc.Pyservice;
import com.cuit.common.rpc.RpcImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.beans.BeanProperty;
import java.util.List;

/**
 * @author dailinfeng
 *
 */
@SpringBootApplication(scanBasePackages = {"com.cuit"})
//@ComponentScan(basePackages = {"com.cuit.common.rpc"})
//@EntityScan("com.cuit.common.pojo")
public class TaskHandleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskHandleApplication.class, args);
    }
//    @Bean
//    public RpcImpl rpc(){
//        return new RpcImpl();
//    }
}

