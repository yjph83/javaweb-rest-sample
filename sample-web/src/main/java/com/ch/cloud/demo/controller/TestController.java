package com.ch.cloud.demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 描述:实现简单的字符串倒序逻辑。
 * 时间:2018-03-05 17:25
 * @author yjph83
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {
    protected static Logger logger= LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value="测试swagger2是否成功", notes="根据用户输入的字符串反向输出")
    @ApiImplicitParam(name = "input", value = "用户输入的字符串", required = true, dataType = "String",paramType = "query")
    @RequestMapping(value = "/test",produces = "application/text;charset=utf-8", method = RequestMethod.POST)
    public String test(@RequestParam String input)
    {
        logger.info(input);
        return new StringBuffer(input).reverse().toString();
    }

    @ApiIgnore
    public String testIgnore(){
        return "success";
    }
}
