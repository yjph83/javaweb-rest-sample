package com.ch.cloud.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yjph83
 * @date 2018/3/21
 */

@FeignClient(name = "exampleService",url = "${exampleService.ribbon.listOfServers}")
@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface RestApi {

    /**
     * 调用远程的Hello服务
     *
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}
