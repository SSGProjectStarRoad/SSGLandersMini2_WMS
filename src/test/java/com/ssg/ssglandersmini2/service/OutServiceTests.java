package com.ssg.ssglandersmini2.service;


import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.service.interfaces.OutcomingService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Log4j2
@SpringBootTest
public class OutServiceTests {

    @Autowired
    OutcomingService outcomingService;

    @Test
    public void testgetList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[] {"id"})
                .keyword("5")
                .build();

        log.info(outcomingService.getList(pageRequestDTO));
    }

    @Test
    public void testgetDetails(){
        log.info(outcomingService.getDetails(1l));
    }

    @Test
    public void modifyStatus(){
        outcomingService.modifyStatus(1l,1l);
    }

    @Test
    public void testgetWaybill(){
        log.info(outcomingService.getWaybill(4l));
    }

    @Test
    public void testShippingcompany(){
        log.info(outcomingService.getShippingcompanyByWbid(1l));
    }

    @Test
    public void modifyWaybillSidByWbidAndSid(){
        outcomingService.modifyWaybillSidByWbidAndSid(1l,3l);
    }
}
