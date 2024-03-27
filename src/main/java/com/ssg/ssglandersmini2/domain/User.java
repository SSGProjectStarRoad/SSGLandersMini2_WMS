package com.ssg.ssglandersmini2.domain;

import lombok.*;


@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String password;
    private String usertype;
    private String name;
    private String address;
    private String telnum;

}
