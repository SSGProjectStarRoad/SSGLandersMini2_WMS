package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
        voList.forEach(log::info);
    }

    @Test
    public void testInsert(){
        Incoming incoming = Incoming.builder()
                .wid(2L)
                .pid(3L)
                .date(LocalDate.now())
                .quantity(20)
                .build();
        incomingMapper.insertOne(incoming);
    }

    @Test
    public void testUpdate(){
        Incoming incoming = Incoming.builder()
                .wid(2L)
                .pid(3L)
                .date(LocalDate.now())
                .quantity(20)
                .approval("입고 승인")
                .status("배송 미완료")
                .build();
        incomingMapper.updateOne(incoming);
    }
    @Test
    public void testDelete(){
        incomingMapper.deleteOne(13L);
    }

    @Test
    public void testStatus(){
        incomingMapper.updateStatus(2L);
    }
}
