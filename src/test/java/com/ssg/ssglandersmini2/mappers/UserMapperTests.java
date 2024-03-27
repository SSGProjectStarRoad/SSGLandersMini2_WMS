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
    public void testInsert() { // 성공!!!
        User user = User.builder()
                .username("testUserInsert3")
                .password("1234")
                .usertype("USER")
                .name("Tester")
                .address("Test Address")
                .telnum("010-1234-5678")
                .build();

        userMapper.insert(user);
    }

    @Test
    public void testSelectOne() { // 성공!!
        Optional<User> user = userMapper.selectOne("testUserInsert");
        user.ifPresent(System.out::println);
        log.info(user);
    }

    @Test
    public void testSelectList() { // 성공!!
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        List<User> userList = userMapper.selectList(pageRequestDTO);
        userList.forEach(System.out::println);
    }

    @Test
    public void testUpdate() { // 성공!!
        User user = User.builder()
                .username("testUserInsert2")
                .password("5678")
                .usertype("ADMIN")
                .name("Tester")
                .address("Test Address")
                .telnum("010-9999-1111")
                .build();

        userMapper.update(user);
    }

    @Test
    public void testDelete() {
        userMapper.delete("testUserInsert");
    } // 성공!!

    @Test
    public void testGetCount() { // 성공!!
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        int count = userMapper.getCount(pageRequestDTO);
        System.out.println("Total count: " + count);
    }
}

