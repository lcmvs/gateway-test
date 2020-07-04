package com.lcm.test.springcloudgatewaytest.config.factory;

import com.lcm.test.springcloudgatewaytest.pojo.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


/**
 * @description:
 * @author: lcm
 * @create: 2020-07-02 11:54
 **/
@Slf4j
@Component
public class MyAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<MyAuthGatewayFilterFactory.Config> {

    private static final String KEY1="global_config";
    private static final String KEY2="lcm_config";

    private static final String ERROR_RESULT="{\"code\":\"500\",\"mes\":\"not have permission\"}";

    public MyAuthGatewayFilterFactory() {
        super(Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY1,KEY2);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("配置：{{}}",config);
                String auth = exchange.getRequest().getHeaders().getFirst("Authorization");
                if(auth==null||!auth.equals("lcm")){
                    ServerHttpResponse response = exchange.getResponse();
                    HttpHeaders httpHeaders = response.getHeaders();
                    httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
                    httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
                    DataBuffer wrap = response.bufferFactory().wrap(ERROR_RESULT.getBytes());
                    return response.writeWith(Mono.just(wrap));
                }else{
                    //
                    return chain.filter(exchange);
                }
            }
        };
    }

    @Data
    public static class Config {
        private String global_config;
        private String lcm_config;
    }

}
