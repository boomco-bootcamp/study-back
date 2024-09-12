package com.lecture.study.app.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityFilter {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //경로별 권한 추가(인가)
        http
                .authorizeHttpRequests((auth) -> auth
                        //.requestMatchers("/", "/login").permitAll()
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("my/**").hasAnyRole("ADMIN","USER")
//                        .anyRequest().authenticated()
                          .anyRequest().permitAll()
                );

//        //로그인 페이지로 이동시키기
//        http
//                .formLogin((auth) -> auth.loginPage("/login") //리다이렉션할 경로 설정
//                        .loginProcessingUrl("/loginProc") //로그인데이터를 넘길 경로 (html에서 post하는 경로)
//                        .successHandler((request, response, authentication) -> {
//                            // 로그인 성공 시 처리할 로직
//                            response.sendRedirect("/"); // 성공 후 리다이렉트할 경로
//                        })
//                        .permitAll()
//                );


        //개발용 csrf 설정 해제
        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }
}
