package com.ssg.ssglandersmini2.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // 페이지 번호를 나타내는 필드. 기본값은 1입니다.
    // page 필드는 페이지 번호를 나타내며, 기본값은 1입니다.
// @Builder.Default 어노테이션은 빌더를 통해 객체를 생성할 때 해당 필드의 기본값을 설정합니다.
// @Min(value = 1) 어노테이션은 page 필드의 최소값이 1임을 나타냅니다.
// @Positive 어노테이션은 page 필드가 양수임을 나타냅니다.
    @Builder.Default // 기본값 설정
    @Min(value = 1) // 최소값
    @Positive // 양수
    private int page = 1;

    // 페이지 크기를 나타내는 필드. 기본값은 10이며, 최소값은 10, 최대값은 100입니다.
    @Builder.Default
    @Min(value = 3)
    @Max(value = 100)
    @Positive
    private int size = 3;

    // 링크 정보를 나타내는 필드
    private String link;

    // 검색 유형을 나타내는 배열 필드
    private String[] types;

    // 검색 키워드를 나타내는 필드
    private String keyword;

    // 완료 여부를 나타내는 필드
    private boolean finished;

    // 시작일을 나타내는 필드
    private LocalDate from;

    // 종료일을 나타내는 필드
    private LocalDate to;

    // 페이지당 건너뛸 항목 수를 계산하는 메서드
    public int getSkip() {
        return (page - 1) * size;
    }

    // 링크 정보를 반환하는 메서드
    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
            link = builder.toString();
        }
        return link;
    }

    // 검색 유형이 주어진 유형과 일치하는지 확인하는 메서드
    public boolean checkType(String type) {
        if (types == null || types.length == 0) {
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }
}

