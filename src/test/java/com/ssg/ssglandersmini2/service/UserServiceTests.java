package com.ssg.ssglandersmini2.service;

import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.service.interfaces.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void registerTest() {
        UserDTO userDTO = UserDTO.builder()
                .username("testUser")
                .password("1234")
                .usertype("user")
                .name("Test User")
                .address("Test Address")
                .telnum("01012345678")
                .build();
        userService.register(userDTO);

    }
    @Test
    public void selectOneTest() {
        log.info(userService.getOne("testUserInsert3"));
    }

    @Test
    public void testSelectList() {

    }

    @Test
    public void updateTest() {
        UserDTO userDTO = UserDTO.builder()
                .username("testUser")
                .password("1234")
                .usertype("user")
                .name("Test User")
                .address("Test Address")
                .telnum("01012345678")
                .build();
        userService.modify(userDTO);
    }

    @Test
    public void removeTest() {
        userService.remove("testUserInsert3");
    }
}
