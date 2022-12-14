package com.swhan.project.web;

import com.swhan.project.config.auth.LoginUser;
import com.swhan.project.config.auth.dto.SessionUser;
import com.swhan.project.service.posts.PostsService;
import com.swhan.project.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    @GetMapping("/") //머스테치 스타터로 인해 경로 및 확장자는 자동 지정됨 -> 경로의 경우 : src/main/resources/templates //확장자의 경우 : .mustache
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //어노테이션 @LoginUser로 수정
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName()); //로그인 성공 시, 세션에 SessionUser를 저장!
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
