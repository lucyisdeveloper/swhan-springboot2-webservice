package com.swhan.project.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity클래스들이 BaseTimeEntity를 상속할 경우, 필드들도 컬럼을 인식하도록 함
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity에 Auditing기능을 포함 시킴
public abstract class BaseTimeEntity {

    @CreatedDate  //Entity가 생성되어 저장될 때, 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate  //Entity가 수정될 때, 시간이 자동 저장됨
    private LocalDateTime modifiedDate;

}
