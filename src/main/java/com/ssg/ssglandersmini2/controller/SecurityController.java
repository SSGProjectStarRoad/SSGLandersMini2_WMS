package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.service.interfaces.SecurityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자가 인증되었고, anonymousUser가 아닌 경우
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            // 이미 로그인된 사용자를 ssglanders/overall 페이지로 리다이렉션
            return "redirect:/ssglanders/overall";
        }

        // 로그인하지 않은 사용자의 경우, 로그인 페이지로 이동
        return "/user/userLogin";
    }

    // 스프링 시큐리티를 사용할 때 로그인 과정(즉, 사용자 인증 과정)은 스프링 시큐리티가 내부적으로 처리합니다.
    // 사용자가 로그인 폼을 제출할 때 발생하는 POST 요청(/user/login으로의 POST 요청)은
    // UserController에 별도로 구현할 필요 없이, 스프링 시큐리티의 UsernamePasswordAuthenticationFilter가 처리합니다.
    // 이 필터는 로그인 폼에서 입력된 사용자 이름과 비밀번호를 바탕으로 사용자 인증을 시도합니다.
    // 따라서, 사용자가 로그인 페이지를 요청하는 GET 요청을 처리하는 부분은 이미 해결되었고,
    // 로그인 폼 제출과 관련된 POST 요청 처리는 스프링 시큐리티가 담당하므로,
    // 이 부분에 대해 추가적인 구현이 필요하지 않습니다.

    // @ResponseBody를 사용함으로써, 스프링은 메소드가 반환하는 데이터(객체, 문자열 등)를 HTTP 응답의 본문으로 직접 반환할 수 있게 되며,
    // 반환되는 데이터 타입에 따라 HttpMessageConverter를 사용하여 적절한 포맷(JSON, XML 등)으로 자동 변환합니다.
    // 예를 들어, 객체를 반환하면 스프링은 설정에 따라 JSON으로 변환하여 응답 본문에 포함시킬 수 있습니다.

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            UserDTO registeredUser = securityService.register(userDTO);
            log.info("회원가입테스트"+registeredUser);
            // 성공적인 처리 메시지와 함께 HTTP 상태 코드 200(OK) 반환
            return ResponseEntity.ok().body(Map.of("message", "회원가입이 성공적으로 처리되었습니다."));
        } catch (Exception e) {
            log.error("회원가입 오류", e);
            // 오류 메시지와 함께 HTTP 상태 코드 500(Internal Server Error) 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "회원가입 처리 중 오류가 발생했습니다."));
        }
    }

    @PostMapping("/find-id")
    @ResponseBody
    public ResponseEntity<?> findUserId(@RequestBody Map<String, String> userData) {
        String name = userData.get("name");
        String phone = userData.get("phone");
        log.info("name : " + name);
        log.info("phone : " + phone);
        String userId = securityService.findUserIdByNameAndPhone(name, phone);
        log.info("userid : " + userId);
        if (userId != null) {
            // 사용자 ID를 찾은 경우, ResponseEntity를 사용하여 JSON 형태로 반환
            return ResponseEntity.ok().body(Map.of("userId", userId));
        } else {
            // 사용자 ID를 찾지 못한 경우, 예외를 던지거나 오류 메시지를 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "사용자를 찾을 수 없습니다."));
        }
    }

    @PostMapping("/find-pw")
    @ResponseBody
    public ResponseEntity<?> findUserPassword(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String telnum = requestBody.get("telnum");

        // 사용자 존재 여부 확인
        boolean userExists = securityService.checkUserExists(username, telnum);
        if (!userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "사용자 정보가 존재하지 않습니다."));
        }

        // 임시 비밀번호 생성 및 업데이트
        String tempPassword = securityService.resetUserPassword(username);
        return ResponseEntity.ok(Map.of("message", "임시 비밀번호가 생성되었습니다.", "tempPassword", tempPassword));
    }

    @PostMapping("/deleteAccount")
    @ResponseBody
    public ResponseEntity<?> deleteAccount() {
        try {
            // SecurityContextHolder를 사용하여 현재 인증된 사용자의 Authentication 객체를 얻음 (보안 상 노출 금지)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName(); // 현재 로그인한 사용자의 username을 얻음

            // void 반환 타입을 사용하고, 문제가 발생하면 예외를 던짐
            securityService.deleteUserByUsername(currentUserName);

            // 성공 응답
            return ResponseEntity.ok().body(Map.of("message", "계정이 성공적으로 삭제되었습니다."));
        } catch (Exception e) {
            // 오류 처리: 사용자 삭제 실패 또는 예외 발생
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "계정 삭제 처리 중 오류가 발생했습니다."));
        }
    }
    @GetMapping("/myInfo")
    public String myInfo(Model model, Principal principal) {
        // SecurityContextHolder를 사용하여 현재 인증된 사용자의 Authentication 객체를 얻음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 현재 로그인한 사용자의 username을 얻음

        // 사용자 정보 조회
        UserDTO userDTO = securityService.getUserByUsername(username);

        // 조회된 사용자 정보를 Model에 추가하여 뷰에 전달
        model.addAttribute("user", userDTO);

        // myInfo.html 페이지를 반환
        return "/user/myInfo"; //물리적인 뷰 템플릿 위치 url 주소 아님!!!
    }
    // /user/myInfo URL에 GET 요청을 할 때 호출되며,
    // 현재 로그인한 사용자의 정보를 조회하여 Model 객체에 추가한 다음,
    // 이 정보를 사용하여 사용자 정보를 보여주는 페이지(myInfo.html)를 렌더링
    @PostMapping("/updateInfo")
    @ResponseBody
    public ResponseEntity<?> updateMyInfo(@RequestBody UserDTO userDTO, Authentication authentication) {
        String currentUserName = authentication.getName();
        // 현재 로그인한 사용자의 username으로 userDTO의 username을 설정
        userDTO.setUsername(currentUserName);
        log.info("Received userDTO: {}", userDTO);

        securityService.updateAdmin(userDTO);
        return ResponseEntity.ok().body(Map.of("message", "정보가 성공적으로 업데이트되었습니다."));
    }
    //사용자가 정보를 수정하고 "수정" 버튼을 클릭하면, AJAX를 통해 이 POST 엔드포인트가 호출

}


