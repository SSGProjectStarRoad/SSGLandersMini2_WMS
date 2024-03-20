package com.ssg.ssglandersmini2.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OverallMapper {
    int getIncomingCount();
}
