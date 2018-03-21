package com.ch.cloud.demo;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 *
 * 描述:swagger配置类，方式1和2同时开启则那个先写，就以那个方式为准；
 * 时间:2018-03-11 13:27
 *
 * @author:yjph83
 */
//注解标示,这是一个配置类,@Configuation注解包含了@Component注解
//可以不用在使用@Component注解标记这是个bean了,
@Configuration
//注解开启 swagger2 功能
@EnableSwagger2
public class SwaggerConfiguration {
      /**
      此为方式一：通过regex("/api.*")通过/api请求路径开头的请求，可以指定某个模块用swagger2 方式生产在线文档；
       */
    @Bean
    public Docket configSpringfoxDocket_all(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json","application/text"))
                .consumes(Sets.newHashSet("application/json","application/text"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .select().paths(regex("/api.*"))
                .build();
    }

    /**
    此为方式二:通过.apis(RequestHandlerSelectors.basePackage("com.ch.cloud.demo.controller"))
    通过包形式来访问
     */
    @Bean
    public Docket createUserInfoRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试swagger2")
                .produces(Sets.newHashSet("application/json","application/text"))
                .consumes(Sets.newHashSet("application/json","application/text"))
                .protocols(Sets.newHashSet("http", "https"))
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo)
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.ch.cloud.demo.controller"))
//                .paths(regex("/user*"))
                .build();
    }

    //构建 api文档的详细信息函数
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Springfox REST API")
                .description("测试swagger2集成是否OK？")
                .termsOfServiceUrl("http://localhost:8080/test")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}