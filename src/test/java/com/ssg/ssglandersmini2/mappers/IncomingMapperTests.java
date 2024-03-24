package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class IncomingMapperTests {

    @Autowired
    private IncomingMapper incomingMapper;


    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[] {"name"})
                .keyword("맥북")
                .build();
        List<Incoming> voList = incomingMapper.selectIncomingList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }
}
