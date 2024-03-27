package com.ssg.ssglandersmini2.mappers;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class overallMapperTest {

    @Autowired
    private OverallMapper overallMapper;

    @Test
    public void testgetIncomingCount(){
        log.info(overallMapper.getIncomingCount());
    }

    @Test
    public void testgetOutcomingCount(){
        log.info(overallMapper.getOutComingCount());
    }

    @Test
    public void testgetIncomingBeforeCount() {log.info(overallMapper.getIncomingBeforeCount());}

    @Test
    public void testgetIncomingArriveCount() {log.info(overallMapper.getIncomingArriveCount());}

    @Test
    public void testgetNotApprovalIncomingCount() {log.info(overallMapper.getNotApprovalIncomingCount());}
    @Test
    public void testgetNotApprovalOutcomingCount() {log.info(overallMapper.getNotApprovalOutcomingCount());}

    @Test
    public void testgetTotalUsingCapacity() {log.info(overallMapper.getTotalUsingCapacity());}

    @Test
    public void testgetWarehouselist() {log.info(overallMapper.getWarehouseList());}

    @Test
    public void testgetIncominglist() {log.info(overallMapper.getIncomingList());}

    @Test
    public void testgetOutcominglist() {log.info(overallMapper.getOutcomingList());}
}
