package com.example.kim_s_cafe.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//빈등록: 스프링 컨테이너에서 객체에서 관리
@EnableWebSecurity/////필터를 추가해준다
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소 접근을 미리체크 한다  이3개는 셋트임 20210520
public class security extends WebSecurityConfigurerAdapter {
 

    @Bean
    public BCryptPasswordEncoder encoderpwd() {

        return new BCryptPasswordEncoder();
    }
   

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
         .csrf().disable()///ajax사용하기 위해 토큰은 나중에
         .authorizeRequests()////요청이발생
         .antMatchers("/","/auth/**")////이 링크들은
         .permitAll()///허용한다
         //.anyRequest()///그외 다른 요청운
         //.authenticated()//인증이있어야한다(로그인)
        .and()
            .formLogin()////로그인 발생시
            .loginPage("/auth/loginpage")///로그인페이지 지정
            .loginProcessingUrl("/auth/loginprocess")//여기로된링크를 가로채서    protected void configure(AuthenticationManagerBuilder auth) 에서 검사한다
            .defaultSuccessUrl("/auth/index")//성공시 여기로보낸다
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/auth/index");///성공한다면 여기
            //////////링크 앞에 항상 /붙여야함
        
            
    }
}
