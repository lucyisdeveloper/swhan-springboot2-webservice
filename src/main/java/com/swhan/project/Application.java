package com.swhan.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
* NAME : Application
* DESC : 메인클래스
* */
//@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication //스프링 부트의 자동 설정, 스프링 Bean 읽기/생성 모두 자동 가능 --> 반드시 최상단에 위치해야함
public class Application {
    public static void main(String[] args){
        //SpringApplication.run() : main내에서 실행 시, 내장 WAS를 실행!
        SpringApplication.run(Application.class, args);
    }
}
