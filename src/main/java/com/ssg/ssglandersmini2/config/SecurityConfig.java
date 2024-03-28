package com.ssg.ssglandersmini2.config;

import groovy.util.logging.Log4j2;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //여기서 HttpSecurity를 구성 이 설정 클래스 내에서 BCryptPasswordEncoder 빈을 정의하는 것이 일반적인 접근 방식
@Log4j2
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호를 안전하게 암호화
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/user/**").permitAll()
                        .anyRequest().authenticated()) // 이 외의 모든 요청은 인증을 요구
                //csrf.ignoringRequestMatchers("/static/**/*").disable()
                .csrf((csrf) -> csrf.disable())
                .headers((headers) -> headers.addHeaderWriter(
                        new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))

                .formLogin((formLogin) -> formLogin //.formLogin() 메서드를 호출하면, UsernamePasswordAuthenticationFilter가 자동으로 등록되고 구성
                        .loginPage("/user/login") // 로그인 페이지 경로 설정
                        .defaultSuccessUrl("/ssglanders/overall", true)// 로그인 성공 시 리다이렉션할 기본 URL 설정
                        .permitAll()// 로그인 페이지는 모두에게 접근 허용

                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 인증되지 않은 사용자가 보호된 페이지에 접근 시 처리
                            response.sendRedirect("/user/login");
                        })
                )

                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //로그아웃 처리 경로
                        .logoutSuccessUrl("/user/login") // 로그아웃 성공시 리다이렉션할 url
                        .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                        .permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // 보안 필터 체인을 거치지 않는 경로 설정 때 사용 정적 리소스에 대한 접근을 보안 검사에서 제외
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

}
