package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectList(PageRequestDTO pageRequestDTO);
    User selectOne(String username);
    void update(User user);
    void delete(String username);
    int getCount(PageRequestDTO pageRequestDTO);

    void SelectSearchList(UserDTO userDTO);
}
