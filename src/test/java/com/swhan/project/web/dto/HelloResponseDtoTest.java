package com.swhan.project.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 5000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); //assertj 테스트검증라이브러리의 검증 메서드 (체이닝 가능)
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
