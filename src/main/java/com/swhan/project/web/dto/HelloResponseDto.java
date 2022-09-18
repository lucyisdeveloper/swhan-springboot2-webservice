package com.swhan.project.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * NAME : HelloResponseDto
 * DESC : 롬복을 사용하여 생성자 없이 DTO 만들기
 * */
@Getter //모든 선언된 필드에 Getter 메서드 생성해줌
@RequiredArgsConstructor //final 선언된 필드에 생성자 만들어줌
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
