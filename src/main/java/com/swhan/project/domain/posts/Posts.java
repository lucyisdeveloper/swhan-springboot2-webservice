package com.swhan.project.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든필드의 getter 자동생성
@NoArgsConstructor //기본생성자 자동생성
@Entity //테이블과 링크가 될 클래스임을 나타냄
public class Posts {

    @Id //테이블의 PK필드 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성규칙 나타냄
    private Long id;

    @Column(length = 500, nullable = false) //굳이 선언하지 않아도 되지만 옵션을 추가할 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //빌더 패턴 클래스 생성으로, 생성자 상단에 선언되면 포함된 필드만 빌더에 포
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
