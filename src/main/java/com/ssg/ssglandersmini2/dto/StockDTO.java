package com.ssg.ssglandersmini2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class StockDTO {
    private long sid;
    private String name;  // 상품명
    private String firstcategory;  //카테고리 대
    private String secondcategory; //카테고리 중
    private String thirdcategory; //카테고리 소
    private int quantity; // 현 재고량
    private String warehousetype; // 상온상태
    private String status; //inventory
    private String wname; // 창고명
    private int usingcapacity;  // 창고 사용량
}
