package com.ch.cloud.demo.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ch.cloud.demo.SpringRestDocsCommTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 描述:
 * 时间:2018-03-13 16:26
 *
 * @author:yjph83
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRestServiceTest extends SpringRestDocsCommTest {
    @Test
    public void a_getUserList() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("pageNum", "1");
        params.add("pageSize", "5");
        params.add("status", "0");
        this.mockMvc.perform(get("/v1/users").params(params))
                .andExpect(status().isOk())
                .andDo(document("users-list", requestParameters(
                        REQUEST_PAGE_NUMBER,
                        REQUEST_PAGE_SIZE,
                        parameterWithName("status").description("用户状态,可以为空")),
                        responseFields(
                                subsectionWithPath("list").type("System对象数组").description("用户列表，具体数据参考根据ID获取用户接口"),
                                RESPONSE_PAGE_NUMBER,
                                RESPONSE_PAGE_SIZE,
                                RESPONSE_SIZE,
                                RESPONSE_START_ROW,
                                RESPONSE_END_ROW,
                                RESPONSE_TOTAL,
                                RESPONSE_PAGES,
                                RESPONSE_PRE_PAGE,
                                RESPONSE_NEXT_PAGE,
                                RESPONSE_IS_FIRST_PAGE,
                                RESPONSE_IS_LAST_PAGE,
                                RESPONSE_HAS_PRE_PAGE,
                                RESPONSE_HAS_NEXT_PAGE,
                                RESPONSE_NAV_PAGES,
                                RESPONSE_NAV_PAGE_NUMBER,
                                RESPONSE_NAV_FIRST_PAGE,
                                RESPONSE_NAV_LAST_PAGE,
                                RESPONSE_LAST_PAGE,
                                RESPONSE_FIRST_PAGE)));
    }


    @Test
    public void b_getUserById() throws Exception {
        this.mockMvc.perform(
                get("/v1/users/{id}", 1))
                .andExpect(status().isOk())
                .andDo(document("users-get",
                        pathParameters(REQUEST_ID),
                        responseFields(
                                subsectionWithPath("id").type("Long").description("用户ID"),
                                subsectionWithPath("name").type("String").description("用户姓名"),
                                subsectionWithPath("age").type("Integer").description("用户年龄"),
                                subsectionWithPath("status").type("Integer").description("用户状态"),
                                subsectionWithPath("description").type("String").description("描述信息"),
                                RESPONSE_CREATE_TIME,
                                RESPONSE_UPDATE_TIME,
                                RESPONSE_OPT_LOCK
                        )));
    }

    @Test
    public void c_saveUser() throws Exception {
        Map<String, Object> user = new HashMap<>();
        user.put("name", "spring-doc");
        user.put("age", 30);
        user.put("status", 0);
        user.put("description", "测试添加新用户");
        this.mockMvc.perform(
                post("/v1/users").contentType("application/json").characterEncoding("UTF-8").content(
                        objectMapper.writeValueAsString(user))).andExpect(
                status().isOk())
                .andDo(document("users-save",
                        requestFields(
                                fieldWithPath("name").type("String").description("用户姓名，不能为空,长度为2个字符到30个字符之间"),
                                fieldWithPath("age").type("Integer").description("用户年龄，不能为空,不能小于0"),
                                fieldWithPath("status").type("Integer").description("用户状态，状态只能是0或1"),
                                fieldWithPath("description").type("String").description("描述，可以为空,不能大于50个字符"))
                ));
    }

    @Test
    public void d_updateUser() throws Exception {
        String responseString = this.mockMvc.perform(
                get("/v1/users/2"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject oldUser = JSON.parseObject(responseString);
        Map<String, Object> user = new HashMap<>();
        user.put("name", "changed name");
        user.put("age", 33);
        user.put("status", 0);
        user.put("description", "change user by spring doc test");
        user.put("optLock", oldUser.getInteger("optLock"));
        this.mockMvc.perform(
                put("/v1/users/{id}", 2).contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andDo(document("users-update",
                        pathParameters(REQUEST_ID),
                        requestFields(
                                fieldWithPath("name").type("String").description("用户姓名，不能为空,长度为2个字符到30个字符之间"),
                                fieldWithPath("age").type("String").description("用户年龄，不能为空,不能小于0"),
                                fieldWithPath("status").type("String").description("用户状态，状态只能是0或1"),
                                fieldWithPath("description").type("String").description("描述，可以为空,不能大于50个字符"),
                                REQUEST_OPT_LOCK)));
    }

    @Test
    public void e_removeUser() throws Exception {
        this.mockMvc.perform(
                delete("/v1/users/{id}", 1))
                .andExpect(status().isOk())
                .andDo(document("users-remove",
                        pathParameters(REQUEST_ID)));
    }
}
