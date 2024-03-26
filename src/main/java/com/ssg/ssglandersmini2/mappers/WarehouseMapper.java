package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseMapper {
    void insertWarehouse(Warehouse warehouse); //새로운 창고를 등록하는 메서드
    List<Warehouse> getAll(); //창고의 전체목록을 불러오는 메서드
    Warehouse getOne(long wid); //id에 해당하는 하나의 창고를 불러오는 메서드
    void update(Warehouse warehouse); //창고를 수정하는 메서드
    void delete(long wid); //id에 해당하는 창고를 삭제하는 메서드

    int getCount(PageRequestDTO pageRequestDTO); //창고 항목의 개수 세는 메소드
    List<Warehouse> selectList(PageRequestDTO pageRequestDTO);

    Warehouse getWarehouse(String wname);
    Warehouse getMaxWid(); //가장 최근에 등록된 창고wid를 가져오기위한 메소드

    void updateWname(@Param("wid") Long wid, @Param("wname") String wname);


}
