package com.ch.cloud.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

/**
 * 描述:该类是生成api文档的公共参数或设置的超类
 * 时间:2018-03-13 16:24
 *
 * @author:yjph83
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRestDocsCommTest {
    //公共参数
    protected static final ParameterDescriptor REQUEST_ID = parameterWithName("id").description("数据ID，不能为空");
    protected static final ParameterDescriptor REQUEST_PAGE_NUMBER = parameterWithName("pageNum").description("页码，第一页页码为1，默认值为1");
    protected static final ParameterDescriptor REQUEST_PAGE_SIZE = parameterWithName("pageSize").description("页长，默认值为10");
    protected static final FieldDescriptor REQUEST_OPT_LOCK = fieldWithPath("optLock").type("Integer").description("数据库乐观锁,不能为空");
    //公共响应
    protected static final FieldDescriptor RESPONSE_OPT_LOCK = fieldWithPath("optLock").type("Integer").description("数据库乐观锁");
    protected static final FieldDescriptor RESPONSE_PAGE_NUMBER = fieldWithPath("pageNum").type("Integer").description("页码");
    protected static final FieldDescriptor RESPONSE_PAGE_SIZE = fieldWithPath("pageSize").type("Integer").description("页长");
    protected static final FieldDescriptor RESPONSE_SIZE = fieldWithPath("size").type("Integer").description("查询总数");
    protected static final FieldDescriptor RESPONSE_START_ROW = fieldWithPath("startRow").type("Integer").description("初始行数");
    protected static final FieldDescriptor RESPONSE_END_ROW = fieldWithPath("endRow").type("Integer").description("结尾行数");
    protected static final FieldDescriptor RESPONSE_TOTAL = fieldWithPath("total").type("Integer").description("总数");
    protected static final FieldDescriptor RESPONSE_PAGES = fieldWithPath("pages").type("Integer").description("总页数");
    protected static final FieldDescriptor RESPONSE_PRE_PAGE = fieldWithPath("prePage").type("Integer").description("前页页数");
    protected static final FieldDescriptor RESPONSE_NEXT_PAGE = fieldWithPath("nextPage").type("Integer").description("后页页数");
    protected static final FieldDescriptor RESPONSE_IS_FIRST_PAGE = fieldWithPath("isFirstPage").type("Boolean").description("是否是第一页");
    protected static final FieldDescriptor RESPONSE_IS_LAST_PAGE = fieldWithPath("isLastPage").type("Boolean").description("是否是最后一页");
    protected static final FieldDescriptor RESPONSE_HAS_PRE_PAGE = fieldWithPath("hasPreviousPage").type("Boolean").description("是否还有前页");
    protected static final FieldDescriptor RESPONSE_HAS_NEXT_PAGE = fieldWithPath("hasNextPage").type("Boolean").description("是否还有后页");
    protected static final FieldDescriptor RESPONSE_NAV_PAGES = fieldWithPath("navigatePages").type("Integer").description("导航页码数");
    protected static final FieldDescriptor RESPONSE_NAV_PAGE_NUMBER = fieldWithPath("navigatepageNums").type("Integer[]").description("所有导航页号");
    protected static final FieldDescriptor RESPONSE_NAV_FIRST_PAGE = fieldWithPath("navigateFirstPage").type("Integer").description("导航第一页");
    protected static final FieldDescriptor RESPONSE_NAV_LAST_PAGE = fieldWithPath("navigateLastPage").type("Integer").description("导航最后页");
    protected static final FieldDescriptor RESPONSE_LAST_PAGE = fieldWithPath("lastPage").type("Integer").description("最后页数");
    protected static final FieldDescriptor RESPONSE_FIRST_PAGE = fieldWithPath("firstPage").type("Integer").description("第一页数");
    protected static final FieldDescriptor RESPONSE_CREATE_TIME = fieldWithPath("createTime").type("Date").description("创建时间");
    protected static final FieldDescriptor RESPONSE_UPDATE_TIME = fieldWithPath("updateTime").type("Date").description("更新时间");

    private String host = "localhost";
    private int port = 8080;
    private String scheme = "http";
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        Snippet[] defaultSnippets = new Snippet[]{CliDocumentation.curlRequest(), CliDocumentation.httpieRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse(), PayloadDocumentation.requestBody(), PayloadDocumentation.responseBody(), HeaderDocumentation.requestHeaders()};
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
                        .snippets().withTemplateFormat(TemplateFormats.asciidoctor()).withEncoding("UTF-8")
                        .withDefaults(defaultSnippets)
                        .and()
                        .uris().withScheme(scheme).withHost(host).withPort(port)
                        .and()
                )
                .build();
    }

    @Test
    public void test() {
        System.out.println("----SpringRestDocsTest:" + host + "---" + port + "---" + scheme);
    }
}
