package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SecurityMapper {

    void insert(User user);

    Optional<User> selectOne(@Param("username") String username);

    List<User> selectList(PageRequestDTO pageRequestDTO);

    void update(User user);

    void delete(String username);

    int getCount(PageRequestDTO pageRequestDTO);

    String findUserIdByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    User findUserByUsernameAndPhone(@Param("username") String username, @Param("telnum") String telnum);
    void updatePassword(@Param("username") String username, @Param("password") String password);

    int deleteUserByUsername(String username);

    void updateAdmin(User user);

    User getUserByUsername(String username);
}

