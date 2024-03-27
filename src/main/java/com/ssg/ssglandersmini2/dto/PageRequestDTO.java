package com.ssg.ssglandersmini2.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public int getSkip() {
        return (page - 1) * 10;
    }

    private String link;
    private String keyword;

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
//    public boolean checkType(String type) {
//        if (types == null || types.length == 0) {
//            return false;
//        }
//        return Arrays.stream(types).anyMatch(type::equals);
//    }
//
//}
}

