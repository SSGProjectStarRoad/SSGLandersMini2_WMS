package com.ssg.ssglandersmini2.service;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.dto.UserUpdateDTO;
import com.ssg.ssglandersmini2.service.interfaces.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;


    @Test
    public void selectOneTest() {
   log.info(userService.getOne("thirduser"));
    }

    @Test
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(2)
                .build();

        PageResponseDTO<UserDTO> responseDTO = userService.getUserList(pageRequestDTO);
        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(userDTO -> log.info(userService));
    }

    @Test
    public void updateTest() {
        UserUpdateDTO userUpdateDTO = UserUpdateDTO.builder()
                .username("gnlwp05")
                .name("휘휘휘")
                .address("가나다라마바사사50")
                .telnum("01054575547")
                .build();
        userService.update(userUpdateDTO);
    }

    @Test
    public void removeTest() {
        userService.delete("fourthuser");
    }
}
