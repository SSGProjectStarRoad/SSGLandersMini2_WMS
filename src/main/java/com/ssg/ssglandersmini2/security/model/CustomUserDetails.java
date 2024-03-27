package com.ssg.ssglandersmini2.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUserDetails extends User {
    private String name;
    private String address;
    private String telnum;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String name, String address, String telnum) {
        super(username, password, authorities);
        this.name = name;
        this.address = address;
        this.telnum = telnum;
    }
}
// CustomUserDetails 객체를 생성할 때 username, password, 권한 정보를 super 생성자를 통해 User 클래스에 전달하고,
// 추가적으로 사용자의 name, address, telnum과 같은 정보를 포함시키면,
// 이 모든 정보를 인증 과정 및 애플리케이션에서 사용할 수 있게 됩니다.
// 이는 Spring Security의 확장성과 유연성을 잘 보여주는 예시입니다.
