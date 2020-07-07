package com.lcm.test.springcloudgatewaytest.controller;

import com.lcm.test.springcloudgatewaytest.pojo.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-06 09:12
 **/
@RestController
public class HystrixController {

    private static final Result FALLBACK_RESULT=new Result();

    {
        FALLBACK_RESULT.setCode((short) 500);
        FALLBACK_RESULT.setMes("服务不可用");
    }

    @RequestMapping("/fallback")
    public Result fallback() {
        return FALLBACK_RESULT;
    }

}
