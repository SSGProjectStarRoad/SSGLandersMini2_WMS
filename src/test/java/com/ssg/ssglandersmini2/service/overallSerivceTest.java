package com.ssg.ssglandersmini2.service;

import com.ssg.ssglandersmini2.service.interfaces.OverallService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class overallSerivceTest {

    @Autowired
    private OverallService overallService;


    @Test
    public void testgetIncomingArriveCountl(){
        log.info(overallService.getIncomingArriveCount());
    }

    @Test
    public void testgetWarehouseCityTotal(){
        log.info(overallService.getWarehouseCityTotal());
    }

    @Test
    public void testgetMonthlyInOut(){log.info(overallService.getMonthlyInOut());}
}
