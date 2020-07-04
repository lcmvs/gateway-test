package com.lcm.test.springcloudgatewaytest.pojo;

import lombok.Data;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-02 14:03
 **/
@Data
public class Result<T> {

    private short code;

    private String mes;

    private T data;
}
