package com.cuit.gateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
public class MyGetWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("************进来了过滤器*******************");
//        RequestPath path = exchange.getRequest().getPath();
//        System.out.println(String.valueOf(path));
//        List<String> allowPath = new ArrayList<String>();
//        allowPath.add("/user/findAll");
//        allowPath.add("/user/login");
//        allowPath.add("/user/register");
//        allowPath.add("/mail/MailServer");
//        if (String.valueOf(path).contains("swagger") || String.valueOf(path).contains("v2")) {
//            System.out.println("通过 swagger");
//            return chain.filter(exchange);
//        }
//        for (String e : allowPath) {
//            if (StringUtils.pathEquals(String.valueOf(path), e)) {
//                System.out.println("************通过了过滤器*******************");
//                return chain.filter(exchange);
//            }
//        }
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        try {
//            List<String> list = headers.get("Authorization");
//            String token = list.get(0);
//            Claims claims = Jwts.parser()
//                    .setSigningKey("xiaocai")
//                    .parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            System.out.println("非法用户");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;  //表示加载过滤器的顺序,值越小优先级越高
    }
}
