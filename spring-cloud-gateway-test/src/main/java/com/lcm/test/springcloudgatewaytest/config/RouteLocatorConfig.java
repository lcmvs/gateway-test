package com.lcm.test.springcloudgatewaytest.config;

import com.lcm.test.springcloudgatewaytest.config.factory.MyAuthGatewayFilterFactory;
import com.lcm.test.springcloudgatewaytest.config.filter.RequestTimeFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-02 11:41
 **/
//@Configuration
public class RouteLocatorConfig {

    //@Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/consumer/**")
                        .filters(f -> f.stripPrefix(2).filter(new RequestTimeFilter()))
                        .uri("lb://EUREKA-CONSUMER")
                        .order(0)
                        .id("customer_filter_router")
                )
                .build();
    }

    @Bean
    public MyAuthGatewayFilterFactory tokenValidateGatewayFilterFactory(){
        return new MyAuthGatewayFilterFactory();
    }

}
