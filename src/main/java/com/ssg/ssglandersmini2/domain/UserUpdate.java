package com.ssg.ssglandersmini2.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {

    private String username;
    private String name;
    private String address;
    private String telnum;

}
