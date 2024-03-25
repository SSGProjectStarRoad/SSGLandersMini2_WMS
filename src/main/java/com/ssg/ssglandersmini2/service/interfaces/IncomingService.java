package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.*;

public interface IncomingService {
    void register(IncomingDTO incomingDTO);
    IncomingListDTO getOne(Long iid);
    //    List<ProductDTO> getList();
    void remove(Long pid);
    void modify(IncomingDTO incomingDTO);
    PageResponseDTO<IncomingListDTO> getIncomingList(PageRequestDTO pageRequestDTO);
}
