package com.ssg.ssglandersmini2.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private String username;
    private String name;
    private String telnum;
    private String address;

}
