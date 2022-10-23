package com.swhan.project.web;

import com.swhan.project.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/*
* NAME : HelloControllerTest
* DESC : HelloController 테스트 지원 컨트롤러
* */
@RunWith(SpringRunner.class) //스프링부트 테스트와 JUnit사이의 연결자 역할(JUnit 외의 SpringRunner.class 실행자를 실행)
@WebMvcTest(controllers = HelloController.class
           ,excludeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes= SecurityConfig.class)})
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello!";

        //1) perform() : MockMVC를 통해 HTTP 요청을 함(안에 get()을 썼으니 HTTP GET 요청임)
        //2) andExpect() : perform() 결과를 검증
        //   [args] - status().isOK() : 응답 200인지 체크
        //          - content().string(hello) : 응답 본문이 "hello"인지 체크
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws  Exception{
        String name = "hello";
        int amount  = 1000;

        //1) param() : 요청파라미터 설정(단, String 밖에 안되어서 String.valueOf() 로 변경 필수)
        //2) jsonPath() : $기준으로 필드명을 명시하고, JSON 응답값을 필드별로 검증해주는 메서드
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
