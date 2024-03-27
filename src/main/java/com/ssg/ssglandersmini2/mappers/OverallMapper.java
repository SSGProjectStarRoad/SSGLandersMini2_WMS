package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.domain.Outcoming;
import com.ssg.ssglandersmini2.domain.Warehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OverallMapper {
    int getIncomingCount();
    int getOutComingCount();

    List<Incoming> getIncomingList();
    List<Outcoming> getOutcomingList();

    int getIncomingBeforeCount();
    int getIncomingArriveCount();

    int getOutcomingBeforeCount();
    int getOutcomingArriveCount();

    int getNotApprovalIncomingCount();
    int getNotApprovalOutcomingCount();

    int getTotalUsingCapacity();

    List<Warehouse> getWarehouseList();
}
