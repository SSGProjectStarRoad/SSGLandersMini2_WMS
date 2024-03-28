package com.ssg.ssglandersmini2.security;


import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.mappers.SecurityMapper;
import com.ssg.ssglandersmini2.security.model.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomUserSecurityService implements UserDetailsService {

    private final SecurityMapper securityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<User> _user = securityMapper.selectOne(username);
       if(_user.isEmpty()){
           throw  new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
       }
       User user = _user.get();
       List<GrantedAuthority> authorities = new ArrayList<>();
       log.info("helloneed :" + user.getUsertype());
        if ("admin".equals(user.getUsertype())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.WAREHOUSE.getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
//securityMapper.selectOne(username)를 통해 사용자 정보를 데이터베이스에서 조회합니다.
//조회된 사용자 정보(User 객체)에서 usertype 필드를 확인합니다.
//usertype 필드의 값에 따라 UserRole 열거형의 getValue() 메서드를 사용하여 SimpleGrantedAuthority 객체를 생성합니다.
//이 객체는 Spring Security에서 사용자의 권한을 나타냅니다.
//usertype이 "admin"이면 UserRole.ADMIN.getValue()를 권한으로 설정합니다.
//그렇지 않으면 UserRole.WAREHOUSE.getValue()를 권한으로 설정합니다.
//마지막으로, org.springframework.security.core.userdetails.User 객체를 생성하여 반환합니다.
//이 객체는 로그인한 사용자의 세부 정보를 담고 있으며, 사용자의 이름, 비밀번호, 그리고 권한 목록을 포함합니다.
