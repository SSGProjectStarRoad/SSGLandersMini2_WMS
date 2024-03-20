package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.mappers.OverallMapper;
import com.ssg.ssglandersmini2.service.interfaces.OverallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OverallServiceImpl implements OverallService {

    private final OverallMapper overallMapper;

    @Override
    public int getIncomingCount() {
        return overallMapper.getIncomingCount();
    }

    @Override
    public int getOutcomingCount() {
        return overallMapper.getOutComingCount();
    }

    @Override
    public int getIncomingBeforeCount() {
        return overallMapper.getIncomingBeforeCount();
    }

    @Override
    public int getIncomingArriveCount() {
        return overallMapper.getIncomingArriveCount();
    }
}
