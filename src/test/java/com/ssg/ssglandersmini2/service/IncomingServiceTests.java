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
}
