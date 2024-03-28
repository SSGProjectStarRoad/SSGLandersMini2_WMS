package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.UserDTO;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;
import com.ssg.ssglandersmini2.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 사용자 목록 조회 페이지 요청 처리
    @GetMapping("/list")
    public String list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사에 실패한 경우, 페이지 요청 DTO를 새로 생성하여 사용
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        System.out.println(pageRequestDTO.getTypes());
        // 사용자 목록 조회 서비스 호출하여 페이지 응답 DTO 획득
        PageResponseDTO<UserDTO> responseDTO = userService.getUserList(pageRequestDTO);
        log.info("내가보낸유저DTO : "+responseDTO);
        // 모델에 사용자 목록 조회 결과를 추가하여 뷰로 전달
        model.addAttribute("responseDTO", responseDTO);

        return "user/userlist";
    }

    // 사용자 정보 상세 조회 페이지 요청 처리
    @GetMapping("/read")
    public String read(@RequestParam("username") String username, PageRequestDTO pageRequestDTO, Model model) {
        // 사용자 정보 조회 서비스 호출하여 사용자 DTO 획득
        UserDTO userDTO = userService.getOne(username);
        log.info(userDTO);
        // 모델에 사용자 DTO 추가하여 뷰로 전달
        model.addAttribute("userDTO", userDTO);

        return "/user/update";
    }

    // 사용자 정보 수정 페이지 요청 처리
    @GetMapping("/update")
    @ResponseBody   //클라이언트로부터 전송된 JSON 데이터를 UserDTO 객체로 변환
    public String update(@RequestParam("username") String username, Model model) {
        // 사용자 정보 조회 서비스 호출하여 사용자 DTO 획득
        UserDTO userDTO = userService.getOne(username);
        log.info("update userlist: " + userDTO);
        // 모델에 사용자 DTO 추가하여 뷰로 전달
        model.addAttribute("userDTO", userDTO);

        return "/user/update";
    }

    // 사용자 정보 수정 요청 처리
    @PostMapping("/update")
    @ResponseBody
    public String update(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // 유효성 검사 실패 시 사용자에게 메시지 전달
            log.info("err: " + bindingResult.getAllErrors());

            return "redirect:/user/userlist?error=validation";
        }
        try {
            // 사용자 정보 업데이트를 시도
            userService.update(userDTO);
            // 성공적으로 수정되었음을 사용자에게 알림
            redirectAttributes.addFlashAttribute("successMessage", "사용자 정보가 성공적으로 수정되었습니다.");

            // 수정 완료 후 목록 페이지로 이동
            return "redirect:/user/list";

        } catch (Exception e) {
            // 수정 중에 오류가 발생한 경우 에러 메시지 전달
            log.error("사용자 정보 수정 중 오류 발생: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 정보를 수정하는 동안 오류가 발생했습니다. 나중에 다시 시도해주세요.");

            // 에러 발생 시 목록 페이지로 이동
            return "redirect:/user/list";
        }
    }

    // 사용자 삭제 요청 처리
    @PostMapping("/delete")
    @ResponseBody    // 반환하는 값이 HTTP 응답 본문에 사용
    public String delete(@RequestBody Map<String, String> requestBody) {
        try {
            // 요청 본문에서 사용자 이름 추출
            String username = requestBody.get("username");
            // 사용자 삭제 비즈니스 로직 호출
            userService.delete(username);

            // 삭제 성공 시 "success" 반환
            return "success";
        } catch (Exception e) {
            // 삭제 과정에서 예외 발생 시 처리
            log.error("사용자 삭제 중 오류 발생: {}", e.getMessage());

            // 삭제 실패 시 "error" 반환
            return "error";

        }
    }
}

