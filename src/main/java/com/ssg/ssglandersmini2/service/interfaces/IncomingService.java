package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.*;

import java.util.List;
import java.util.Map;

public interface IncomingService {
    void register(IncomingDTO incomingDTO);

    void remove(Long iid);
    void modify(IncomingDTO IncomingDTO);
    PageResponseDTO<IncomingListDTO> getIncomingList(PageRequestDTO pageRequestDTO);
    PageResponseDTO<IncomingListDTO> getIncomingNotApprovalList(PageRequestDTO pageRequestDTO);

    List<Map<String, String>> getWarehouseListByType(String type);
    Long getWidByWarehouseName(String wname);
    void modifyApprovalByIid(Long iid);
    void changeStatus(Long iid);
}
