package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.StockDTO;

import java.util.List;

public interface StockMapper {
    List<StockDTO> getWarehouse();
    List<StockDTO> selectAll();
    int getCount(PageRequestDTO pageRequestDTO);
    List<StockDTO> selectList(PageRequestDTO pageRequestDTO);
    List<StockDTO> getWarehouseList( );


}
