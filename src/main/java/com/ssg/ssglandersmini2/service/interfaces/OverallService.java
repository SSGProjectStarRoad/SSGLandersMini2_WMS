package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.MonthlyDTO;
import com.ssg.ssglandersmini2.dto.WarehouseCityDTO;

import java.util.List;
import java.util.Optional;

public interface OverallService {

    Optional<Integer> getIncomingCount();

    Optional<Integer> getOutcomingCount();

    Optional<Integer> getIncomingBeforeCount();

    Optional<Integer> getIncomingArriveCount();

    Optional<Integer> getOutcomingBeforeCount();

    Optional<Integer> getOutcomingArriveCount();

    Optional<Integer> getNotApprovalCount();

    Optional<Integer> getTotalUsingCapacity();

    List<WarehouseCityDTO> getWarehouseCityTotal();

    MonthlyDTO getMonthlyInOut();

}
