package com.ch.cloud.demo.controller;

import com.ch.cloud.demo.service.RestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjph83
 * @date 2018/3/21
 */

@RestController
public class RemoteController {
    @Autowired
    RestApi restApi;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return restApi.hello(name);
    }
}
