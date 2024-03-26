package com.ssg.ssglandersmini2.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Positive
    @Min(value = 10)
    @Max(value = 100)
    private int size = 10;

    // 링크 정보를 나타내는 필드
    private String link;

    // 검색 유형을 나타내는 배열 필드
    private String[] types;

    // 검색 키워드를 나타내는 필드
    private String keyword;

    // 완료 여부를 나타내는 필드
    private boolean finished;

    // 페이지당 건너뛸 항목 수를 계산하는 메서드
    public int getSkip(){
        return (page-1) * 10;
    }

    // 링크 정보를 반환하는 메서드
    public String getLink() {
        if(link == null){
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
