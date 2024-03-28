package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest // Spring Boot 테스트 환경을 위한 설정
@ActiveProfiles("test") // 테스트 환경에서 사용할 프로필 지정 (선택 사항)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        List<User> userList = userMapper.selectList(pageRequestDTO);
        userList.forEach(System.out::println);
    }
    @Test
    public void testSelectOne() {
        String username = "testUser";
        User user = userMapper.selectOne(username);
        System.out.println(user);
    }

    @Test
    public void testUpdate() {
        User user = User.builder()
                .username("user70")
                .password("aaaa")
                .usertype("user")
                .name("헷갈리네")
                .address("Test Address")
                .telnum("010-9991111")
                .build();

        userMapper.update(user);
    }

    @Test
    public void testDelete() {
        userMapper.delete("user97");
    }

    @Test
    public void testselectList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                        .page(1)
                                .size(10)
                .types(new String[] {"username"})
                .keyword("user15")
                                        .build();
        List<User> voList = userMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }

}

