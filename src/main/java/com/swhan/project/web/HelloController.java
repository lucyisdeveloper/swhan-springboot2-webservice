package com.swhan.project.web;

import com.swhan.project.web.dto.HelloResponseDto;
import  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import  org.springframework.web.bind.annotation.RestController;

/*
* NAME : HelloController
* DESC : 테스트 지원 API
* */
@RestController //컨트롤러를 JSON으로 반환 (@ResponseBody를 각 메소드마다 선언했지만 이것으로 대체 ok)
public class HelloController {
    @GetMapping("/hello") //GET방식 API
    public String hello(){
        return "hello!";
    }

    @GetMapping
    public HelloResponseDto helloDto(@RequestParam("name") String name
            , @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
