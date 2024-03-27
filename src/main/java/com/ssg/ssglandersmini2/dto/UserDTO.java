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

    @NotBlank// Username cannot be empty 문자열에만 적용 공백 문자만 있는 경우도 유효하지 않음
    @Size(min=1,max=20)
    private String username;

    @Size(min=8)
    private String password;

    private String usertype;

    @NotBlank
    @Size(min=1,max=50)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9\\s,.'-]{10,100}$")
    private String address;

    @Pattern(regexp = "^010\\d{8}$")
    private String telnum;

    private String keyword;

    private String type;

}
