package com.ssg.ssglandersmini2.dto;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

// PageResponseDTO 클래스는 페이지 응답 정보를 표현하는 DTO(Data Transfer Object)입니다.

// @Getter 어노테이션은 모든 필드에 대한 getter 메서드를 자동으로 생성합니다.
@Getter
// @ToString 어노테이션은 toString() 메서드를 자동으로 생성하여 객체의 문자열 표현을 제공합니다.
@ToString
public class PageResponseDTO<E> {
    // 현재 페이지 번호를 나타내는 필드
    private int page;
    // 페이지 크기를 나타내는 필드
    private int size;
    // 전체 항목 수를 나타내는 필드
    private int total;
    // 시작 페이지 번호를 나타내는 필드
    private int start;
    // 끝 페이지 번호를 나타내는 필드
    private int end;
    // 이전 페이지의 존재 여부를 나타내는 필드
    private boolean prev;
    // 다음 페이지의 존재 여부를 나타내는 필드
    private boolean next;
    // DTO(Data Transfer Object) 리스트를 나타내는 필드
    private List<E> dtoList;

    // 생성자
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        // 현재 페이지 번호를 설정
        this.page = pageRequestDTO.getPage();
        // 페이지 크기를 설정
        this.size = pageRequestDTO.getSize();
        // 전체 항목 수를 설정
        this.total = total;
        // 페이지 계산
        this.end = (int)(Math.ceil(this.page / 10.0 )) * 10;
        this.start = this.end - 9;
        // 마지막 페이지 번호 계산
        int last = (int)(Math.ceil((total/(double)size)));
        this.end = end > last ? last: end;
        // 이전 페이지의 존재 여부 설정
        this.prev = this.start > 1;
        // 다음 페이지의 존재 여부 설정
        this.next = total > this.end * this.size;
        // DTO 리스트 설정
        this.dtoList = dtoList;
    }
}