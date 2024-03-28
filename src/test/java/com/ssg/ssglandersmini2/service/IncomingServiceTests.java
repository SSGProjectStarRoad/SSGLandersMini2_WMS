package com.ssg.ssglandersmini2.service;


import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.dto.IncomingDTO;
import com.ssg.ssglandersmini2.dto.IncomingListDTO;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.service.interfaces.IncomingService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class IncomingServiceTests {

    @Autowired
    private IncomingService incomingService;


    @Test
    public void testGetList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        log.info(incomingService.getIncomingList(pageRequestDTO));
    }

    @Test
    public void testRegister(){
        IncomingDTO incomingDTO = IncomingDTO.builder()
                .pid(3L)
                .wid(3L)
                .date(LocalDate.now())
                .quantity(20)
                .build();

        incomingService.register(incomingDTO);
    }

    @Test
    public void testModify(){
        IncomingListDTO incomingListDTO = IncomingListDTO.builder()
                .pid(2L)
                .wid(3L)
                .date(LocalDate.now())
                .name("hosang")
                .firstcategory("식품")
                .secondcategory("라면")
                .thirdcategory("진라면")
                .palletperquantity(80L)
                .approval("입고 승인")
                .status("배달 완료")
                .type("상온")
                .build();

//        incomingService.modify(incomingListDTO);
    }

    @Test
    public void testRemove(){
        incomingService.remove(13L);
    }

    @Test
    public void testStatus(){
        incomingService.changeStatus(3L);
    }
}
