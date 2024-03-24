package com.ssg.ssglandersmini2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;

    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;

    //다음 페이지의 존재 여부
    private boolean next;
    private List<E> dtoList;

    @Builder(builderMethodName = "All")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total =total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0 )) * 10;
        this.start = this.end - 9;

        int last = (int)(Math.ceil((total/(double)size)));
        this.end = Math.min(end, last);

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }
    // 생성자의 prev,next start end 값이 list.jsp 에서 상황에 따라 값이 지속적으로 변경
//    this.page 의 지속적 변화로 다른 값도 변화가 발생하는것
}
