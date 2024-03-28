package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.domain.UserUpdate;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.dto.UserUpdateDTO;
import com.ssg.ssglandersmini2.mappers.UserMapper;
import com.ssg.ssglandersmini2.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    // 사용자 목록 조회 서비스 메서드
    @Override
    public PageResponseDTO<UserDTO> getUserList(PageRequestDTO pageRequestDTO) {

        // 페이지에 해당하는 사용자 목록을 조회
        List<User> userList = userMapper.selectList(pageRequestDTO);

        // User 엔티티를 UserDTO로 변환
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        // 전체 사용자 수 조회
        int totalCount = userMapper.getCount(pageRequestDTO);

        // 페이지 응답 DTO를 생성하여 반환
        PageResponseDTO<UserDTO> pageResponseDTO = PageResponseDTO.<UserDTO>withAll()
                .dtoList(userDTOList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();

        // PageResponseDTO에 결과 설정하여 반환
        return pageResponseDTO;
    }

    // 특정 사용자 조회 서비스 메서드
    @Override
    public UserDTO getOne(String username) {
        // 주어진 사용자 이름에 해당하는 사용자 정보를 조회하고 UserDTO로 변환하여 반환
        User user = userMapper.selectOne(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    // 사용자 정보 수정 서비스 메서드
    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        log.info("유저 update: " + userUpdateDTO);

        // UserDTO를 User 엔티티로 변환하여 업데이트 수행
        UserUpdate userUpdate = modelMapper.map(userUpdateDTO, UserUpdate.class);
        log.info("유저!!"+userUpdate);
        userMapper.update(userUpdate);
    }

    // 사용자 삭제 서비스 메서드
    @Override
    public void delete(String username) {
        // 주어진 사용자 이름에 해당하는 사용자를 삭제
        userMapper.delete(username);
    }

    // 사용자 검색 서비스 메서드
    @Override
    public List<UserDTO> getSearchList(UserDTO userDTO) {
        // 주어진 사용자 DTO로 검색된 사용자 목록을 조회하고 UserDTO로 변환하여 반환
        List<User> userList = userMapper.selectSearchList(userDTO);
        return userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

}
