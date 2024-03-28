package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.User;
import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.exception.ApplicationException;
import com.ssg.ssglandersmini2.mappers.SecurityMapper;
import com.ssg.ssglandersmini2.service.interfaces.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService{

    private final SecurityMapper securityMapper;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO) { //사용자 생성
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); //userDTO에서 암호화 먼저 하기
        User user = modelMapper.map(userDTO, User.class);
        securityMapper.insert(user); // 사용자 정보 저장
        log.info("Created user: {}", user);
        return modelMapper.map(user, UserDTO.class); // 생성된 User 객체를 UserDTO로 변환하여 반환
    }
    @Override
    public String findUserIdByNameAndPhone(String name, String phone) { // 아이디 찾기(이름,핸드폰 번호 입력받아서)
        return securityMapper.findUserIdByNameAndPhone(name, phone);
    }


    @Override
    public User getOne(String username) { // 사용자 조회
        Optional<User> user = securityMapper.selectOne(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public void modify(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        securityMapper.update(user);
    }

    @Override
    public void remove(String username) {
        securityMapper.delete(username);
    }

    // 임시 비밀번호 만들기
    @Override
    public boolean checkUserExists(String username, String telnum) {
        return securityMapper.findUserByUsernameAndPhone(username, telnum) != null;
    }

    @Override
    public String resetUserPassword(String username) {
        String tempPassword = RandomStringUtils.randomAlphanumeric(8);
        String encodedPassword = passwordEncoder.encode(tempPassword);
        securityMapper.updatePassword(username, encodedPassword);
        return tempPassword;
    }

    @Override
    public void deleteUserByUsername(String username) {
        try {
            // int 반환 타입을 사용하는 것은 이 메서드가 삭제 쿼리를 실행한 후 삭제된 행의 수를 반환할 것이라는 의미
            // 해당 사용자가 데이터베이스에 존재하고 삭제가 성공적으로 수행되면 1(또는 삭제된 행의 수)을 반환하고,
            // 사용자가 존재하지 않아 삭제할 행이 없으면 0을 반환
            int deletedRows = securityMapper.deleteUserByUsername(username);
            if (deletedRows == 0) {
                // 삭제할 사용자가 데이터베이스에 없는 경우
                throw new ApplicationException(ApplicationException.ErrorCode.USER_NOT_FOUND, "해당 사용자를 찾을 수 없습니다: " + username);
            }
        } catch (DataAccessException e) {
            // 데이터베이스 액세스 중 발생한 예외 처리
            throw new ApplicationException(ApplicationException.ErrorCode.DATABASE_ERROR, "데이터베이스 작업 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void updateAdmin(UserDTO userDTO) {
        // userDTO에서 비밀번호를 가져와 암호화
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        // 암호화된 비밀번호를 userDTO에 다시 설정
        userDTO.setPassword(encodedPassword);
        // userDTO를 User 도메인 객체로 변환
        User user = modelMapper.map(userDTO, User.class);
        // 암호화된 비밀번호를 가진 User 객체로 데이터베이스 업데이트
        securityMapper.updateAdmin(user);
        log.info("Updated admin user: {}", user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        // UserMapper를 사용하여 데이터베이스에서 사용자 정보를 조회
        User user = securityMapper.getUserByUsername(username);
        if (user == null) {
            throw new ApplicationException(ApplicationException.ErrorCode.USER_NOT_FOUND, "해당 사용자를 찾을 수 없습니다: " + username);
        }
        // 조회된 User 도메인 객체를 UserDTO로 변환하여 반환
        return modelMapper.map(user, UserDTO.class);
    }

    //RandomStringUtils는 Apache Commons Lang 라이브러리에 포함된 유틸리티 클래스
    //문자열 관련 다양한 유틸리티 메서드를 제공하는데, 그 중 하나가 랜덤 문자열을 생성하는 메서드
    //RandomStringUtils.randomAlphanumeric(int count) 메서드는 알파벳(대소문자)과 숫자를 포함한 랜덤 문자열을 생성하여 반환
    //여기서 count 매개변수는 생성할 문자열의 길이를 지정
    //이 유틸리티 클래스를 사용하기 위해서는 Apache Commons Lang 라이브러리를 프로젝트에 추가해야
    //build.gradle 파일에 다음 의존성을 추가
    //dependencies {implementation 'org.apache.commons:commons-lang3:3.12.0'} // 사용하려는 버전으로 변경 가능
    //import org.apache.commons.lang3.RandomStringUtils;

}
