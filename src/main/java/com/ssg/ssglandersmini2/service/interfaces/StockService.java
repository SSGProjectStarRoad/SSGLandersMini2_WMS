package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.domain.Stock;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.StockDTO;
import com.ssg.ssglandersmini2.mappers.StockMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {
    Long register(StockDTO stockDTO);
    Page<Stock> serchAll(String[] types, String keyword, Pageable pageable);
    long getCount(String[] types, String keyword);
    List<StockDTO> getAll();
    PageResponseDTO<StockDTO> pagelist(PageRequestDTO pageRequestDTO);
PageResponseDTO<StockDTO> getList(PageRequestDTO pageRequestDTO); // 페이징 처리
    public List<StockDTO> getWareHouse();

    // 검색, 리스트 띄우기, 변환, 출고요청 데이터 리스트에 담아 넘기기
}
