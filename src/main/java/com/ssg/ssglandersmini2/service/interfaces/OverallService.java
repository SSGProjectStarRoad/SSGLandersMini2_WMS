package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.MonthlyDTO;
import com.ssg.ssglandersmini2.dto.WarehouseCityDTO;

import java.util.List;

public interface OverallService {

    int getIncomingCount();

    int getOutcomingCount();

    int getIncomingBeforeCount();

    int getIncomingArriveCount();

    int getNotApprovalCount();

    int getTotalUsingCapacity();

    List<WarehouseCityDTO> getWarehouseCityTotal();

    MonthlyDTO getMonthlyInOut();

}
