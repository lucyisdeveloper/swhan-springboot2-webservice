package config.auth;

import com.swhan.project.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //h2-console화면을 사용하기 위해 해당 옵션들을 disalbe함
        http.csrf().disable().headers().frameOptions().disable()
                //authorizeRequests() : URL별 권한 관리를 설정하는 옵션의 시작점 -> 이게 있어야만 antMatchers옵션 사용가능
                .and().authorizeRequests()
                //antMatchers() : 권한관리 대상을 지정하는 옵션 -> HTTP,URL메소드별로 관리가 가능
                // / :  permitAll() 옵션을 통해 전체 열람 권한을 줌
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                // /api/v1/** : USER권한을 가진사람만 가능함
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //anyRequst() : 설정된 값들 이외 나머지 URL을 나타냄
                //authenticated() : 나머지 URL들은 모두 인증된 사용자들(로그인한 사용자)에게만 허용하게 함
                .anyRequest().authenticated()
                //로그아웃 기능 중 로그아웃 성공시 / 로 이동
                .and().logout().logoutSuccessUrl("/")
                //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .and().oauth2Login()
                //OAuth2 로그인 성공 후, 사용자 정보를 가져올 때의 설정들을 담당
                .userInfoEndpoint()
                //로그인 성공후, 후속조치를할 UserService인터페이스의 구현체 등록
                .userService(customOAuth2UserService);
    }
}
