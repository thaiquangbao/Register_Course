package com.iuh.springgatewayservice.filter;

import com.iuh.springgatewayservice.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.io.ObjectInputFilter;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    public static class Config {
    }
    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JWTUtils jwtUtils;




    public AuthFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
           if (routerValidator.isSecured.test(exchange.getRequest())){
               if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                   throw new RuntimeException("missing authorization header");
               }
               String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
               if (authHeader!=null && authHeader.startsWith("Bearer ")){
                   authHeader = authHeader.substring(7);
               }
               System.out.println("Bearer "+authHeader);
               System.out.println(jwtUtils.generateToken(authHeader));
               try {
                    jwtUtils.validateToken(authHeader);
                    request = exchange.getRequest()
                           .mutate()
                           .header("loggedUser", jwtUtils.generateToken(authHeader))
                            .build();
               }catch (Exception e){
                   throw new RuntimeException("un authorized access to application");
               }

           }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }
}
