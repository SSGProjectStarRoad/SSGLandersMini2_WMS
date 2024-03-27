package com.ssg.ssglandersmini2.security.model;

import lombok.Getter;

@Getter
public enum UserRole {
    WAREHOUSE("ROLE_WAREHOUSE"), // 창고 관리자
    ADMIN("ROLE_ADMIN"); // 총 관리자

    private final String value; // 불변성!
    UserRole(String value) {
        this.value = value;
    }
    // value 값으로부터 UserRole을 찾아내는 유틸리티 메소드
    public static UserRole fromValue(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
//  각 역할에 대한 문자열 값을 저장하는 value 필드를 가짐.
//  생성자를 통해 이 값을 초기화하고, getValue() 메서드를 통해 외부에서 접근가능.
// UserRole 열거형을 사용하면, 역할을 안전하게 관리하고 코드 내에서 참조할 때 오류의 가능성을 감소.

// fromValue 메서드는 특정 문자열 값에 해당하는 UserRole 열거형 상수를 찾는 유용한 방법을 제공합니다.
// 이 메서드의 사용 여부는 어플리케이션의 특정 요구사항과 구현 상황에 따라 달라집니다.
// 만약 클라이언트로부터 전달받은 문자열 값을 열거형 상수로 변환해야 하는 상황이 있다면, fromValue 메서드를 활용하는 것이 좋습니다.
