package com.swhan.project.web.domain.posts;

import com.swhan.project.domain.posts.Posts;
import com.swhan.project.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정 없이 해당 어노테이션을 사용하면 H2 DB 를 자동으로 실행함.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After  //Junit에서 단위테스트 끝나 때마다 수행되는 메소드 지정
            //배포 전, 전체 테스트 수행도중 테스트 간의 데이터 침범을 막아 테스트 실패의 경우를 막아줌
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //save() : insert, update 쿼리 실행
        //       -> id 값이 있다면 insert
        //       -> 그렇지 않다면 update 실행
        postsRepository.save(Posts.builder().title(title)
                                            .content(content)
                                            .author("starwing9997@gmail.com")
                                            .build());

        //when
        //findAll() : select * 쿼리
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
