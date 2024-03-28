package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.domain.UserUpdate;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 사용자 목록 조회를 위한 메서드
    List<User> selectList(PageRequestDTO pageRequestDTO);

    // 특정 사용자 정보 조회를 위한 메서드
    User selectOne(String username);

    // 사용자 정보 수정을 위한 메서드
    void update(UserUpdate userUpdate);

    // 사용자 삭제를 위한 메서드
    void delete(String username);

    // 페이지 별 사용자 수 조회를 위한 메서드
    int getCount(PageRequestDTO pageRequestDTO);

    // 사용자 검색을 위한 메서드
    List<User> selectSearchList(UserDTO userDTO);

}
