package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;

import java.util.List;

public interface UserService {

    // 사용자 목록을 페이징하여 조회하는 메서드
    PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO);

    // 주어진 사용자 이름에 해당하는 단일 사용자 정보를 조회하는 메서드
    UserDTO getOne(String username);

    // 주어진 사용자 정보를 수정하는 메서드
    void update(UserDTO userDTO);

    // 주어진 사용자 이름에 해당하는 사용자를 삭제하는 메서드
    void delete(String username);

    // 주어진 사용자 DTO에 따라 검색된 사용자 목록을 조회하는 메서드
    List<UserDTO> getSearchList(UserDTO userDTO);
}
