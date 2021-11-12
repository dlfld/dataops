package com.cuit.gateway.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dailinfeng
 */
@Slf4j
@Component
public class MyGetWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("************进来了过滤器*******************");
        RequestPath path = exchange.getRequest().getPath();
        List<String> allowPath = new ArrayList<String>();

//        allowPath.add("/sso/login");
        allowPath.add("/sso/register");
        if (String.valueOf(path).contains("swagger") || String.valueOf(path).contains("v3")) {
            log.info("通过 swagger");
            //放行
            return chain.filter(exchange);
        }
        for (String e : allowPath) {
            if (StringUtils.pathEquals(String.valueOf(path), e)) {
                log.info("************通过了过滤器*******************");
                return chain.filter(exchange);
            }
        }
        HttpHeaders headers = exchange.getRequest().getHeaders();
        try {
            log.info("进来了过滤器");
            List<String> list = headers.get("Authorization");
            String token = list.get(0);
            /**
             * token校验，如果token是正确的就在header上加上user-userName
             */
            try {
                Algorithm algorithm = Algorithm.HMAC256("idse2020");
                JWTVerifier verifier = JWT.require(algorithm).build();
                @SuppressWarnings("unused")
                DecodedJWT jwt = verifier.verify(token);
                //到这一步没有报错的话就表示toke正确，添加header
                DecodedJWT jwt1 = JWT.decode(token);
                String userName = jwt1.getClaim("userName").asString();
                //向headers中放文件，记得build
                ServerHttpRequest host = exchange.getRequest().mutate().header("userName", userName).build();
                //将现在的request 变成 change对象
                ServerWebExchange build = exchange.mutate().request(host).build();
                return chain.filter(build);
            } catch (Exception e) {
                return exchange.getResponse().setComplete();
            }
        } catch (Exception e) {
            System.out.println("非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        //return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //表示加载过滤器的顺序,值越小优先级越高
        return 0;
    }
}
