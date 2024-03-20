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

}
