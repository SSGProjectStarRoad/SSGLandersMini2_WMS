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
//                .types(new String[] {"name"})
//                .keyword("맥북")
                .build();

        log.info(outcomingService.getList(pageRequestDTO));
    }

    @Test
    public void testgetDetails(){
        log.info(outcomingService.getDetails(1l));
    }

}
