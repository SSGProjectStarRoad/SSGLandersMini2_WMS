package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;

import java.util.List;

public interface IncomingMapper {
    List<Incoming> selectIncomingList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
}
