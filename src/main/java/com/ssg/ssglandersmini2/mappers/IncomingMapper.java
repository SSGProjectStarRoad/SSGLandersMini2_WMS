package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;

import java.util.List;
import java.util.Map;

public interface IncomingMapper {
    List<Incoming> selectIncomingList(PageRequestDTO pageRequestDTO);
    List<Incoming> selectIncomingNotApprovalList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
    int getCount2(PageRequestDTO pageRequestDTO);
    void insertOne(Incoming incoming);
    void deleteOne(Long iid);
    void updateOne(Incoming incoming);
    List<Map<String, String>> findListByWarehouseType(String type);
    Long findWidByWarehouseName(String wname);
    void updateApprovalByIid(Long iid);
    void updateStatus(Long iid);
    int getWarehouseQuantity(Long wid);
}
