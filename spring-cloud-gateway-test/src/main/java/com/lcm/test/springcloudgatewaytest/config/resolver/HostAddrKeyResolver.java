package com.lcm.test.springcloudgatewaytest.config.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-04 09:48
 **/
@Slf4j
//@Component
public class HostAddrKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String str=exchange.getRequest()
                .getRemoteAddress()
                .getAddress()
                .getHostAddress();
        log.info(str);
        return Mono.just(str);
    }

}
