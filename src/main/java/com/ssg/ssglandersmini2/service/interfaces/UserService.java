package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;

import java.util.List;

public interface UserService {

    //사용자 목록 조회
    PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO);

    //특정 사용자 조회
    UserDTO getOne(String username);

    //사용자 정보 수정
    void update(UserDTO userDTO);

    //사용자 삭제
    void delete(String username);

    List<UserDTO> getSearchList(UserDTO userDTO);
}
