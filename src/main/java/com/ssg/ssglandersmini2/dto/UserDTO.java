package com.ssg.ssglandersmini2.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "아이디를 입력하세요.")
    @Size(min = 5, max = 20, message = "아이디는 최소 5자 이상, 최대 20자 이하로 입력해야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 허용됩니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min=8, message = "비밀번호는 최소 8자 이상 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "비밀번호는 최소 8자리 이상, 영문, 숫자, 특수문자가 모두 포함되어야 합니다.")
    private String password;

    private String usertype;

    @NotBlank(message = "이름을 입력하세요.")
    @Size(min=1,max=10, message = "이름은 최대 10자까지 입력 가능합니다.")
    private String name;

    @NotNull
    @NotBlank(message = "주소를 입력하세요.")
    @Size(min = 10, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9\\s,.'-]{10,100}$")
    private String address;

    @Pattern(regexp = "^010\\d{8}$", message = "올바른 전화번호 형식이 아닙니다.")
    private String telnum;

}
