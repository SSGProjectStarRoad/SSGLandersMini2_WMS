package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Stock;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.StockDTO;
import com.ssg.ssglandersmini2.mappers.StockMapper;
import com.ssg.ssglandersmini2.service.interfaces.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
private final StockMapper stockMapper;
private final ModelMapper modelMapper;

    @Override
    public Long register(StockDTO stockDTO) {
        return null;
    }

    @Override
    public Page<Stock> serchAll(String[] types, String keyword, Pageable pageable) {
//        int offset = pageable.getPageNumber() * pageable.getPageSize();
//        int limit = pageable. getPageSize();
//        List<Stock> list = stockMapper.searchAll(types, keyword, offset, limit);
////        long count = bo
        return null;
    }

    @Override
    public long getCount(String[] types, String keyword) {
        return 0;
    }

    @Override
    public PageResponseDTO<StockDTO> pagelist(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public PageResponseDTO<StockDTO> getList(PageRequestDTO pageRequestDTO) {
        List<StockDTO> dtoList = stockMapper.selectList(pageRequestDTO).stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());

        int total = stockMapper.getCount(pageRequestDTO);

        PageResponseDTO<StockDTO> pageResponseDTO = PageResponseDTO.<StockDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public List<StockDTO> getAll() {
        List<StockDTO> dtoList = stockMapper.selectAll().stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
    public List<StockDTO> getWareHouse(){
        List<StockDTO> whList = stockMapper.getWarehouse().stream().map(wr -> modelMapper.map(wr, StockDTO.class))
                .collect(Collectors.toList());
        return whList;
    }

}
