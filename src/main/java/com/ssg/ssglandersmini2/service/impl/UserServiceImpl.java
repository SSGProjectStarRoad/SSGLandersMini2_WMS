package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.mappers.UserMapper;
import com.ssg.ssglandersmini2.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    //사용자목록 조회
    @Override
    public PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO) {

        // 사용자 목록을 페이징하여 조회
        List<User> userList = userMapper.selectList(pageRequestDTO);

        // User 엔티티를 UserDTO로 변환
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

        // 전체 사용자 수 조회
        int totalCount = userMapper.getCount(pageRequestDTO);

        PageResponseDTO<UserDTO> pageResponseDTO = PageResponseDTO.<UserDTO>withAll()
                .dtoList(userDTOList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();

        // PageResponseDTO에 결과 설정하여 반환
        return pageResponseDTO;
    }

    //특정 사용자 조회
    @Override
    public UserDTO getOne(String username) {
        User user = userMapper.selectOne(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    //사용자 정보 수정
    @Override
    public void update(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userMapper.update(user);
    }

    //사용자 삭제
    @Override
    public void delete(String username) {
        userMapper.delete(username);
    }

    @Override
    public List<UserDTO> getSearchList(UserDTO userDTO) {
        return null;
    }

//    @Override
//    public List<UserDTO> getSearchList(UserDTO userDTO) {
//        userMapper.SelectSearchList(userDTO);
//
//    }
}
