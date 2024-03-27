package com.ssg.ssglandersmini2.mappers;


import com.ssg.ssglandersmini2.domain.Outcoming;
import com.ssg.ssglandersmini2.domain.Product;
import com.ssg.ssglandersmini2.domain.Stock;
import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@Log4j2
@SpringBootTest
@SpringJUnitConfig
//@ContextConfiguration(locations="file:src/main/resources/application.properties")
//@ContextConfiguration(classes = TestConfig.class)
public class OutcomingTests {

    @Autowired
    OutcomingMapper outcomingMapper;

    @Test
    public void testSelectList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[] {"id"})
                .keyword("15")
                .build();
        List<Outcoming> voList = outcomingMapper.selectOutcomingList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));

    }

    @Test
    public void testGetProductName(){
        Product product = outcomingMapper.getProductByPid(1l);
        log.info(product);
    }

    @Test
    public void testGetProductNameByPid(){
        log.info(outcomingMapper.getProductNameByPid(1l));
    }

    @Test
    public void testGetWarehouseByWid(){
        Warehouse warehouse = outcomingMapper.getWarehouseByWid(1l);
        log.info(warehouse);
    }

    // 디테일

    @Test
    public void testgetProductByOid(){
        log.info(outcomingMapper.getProductByOid(1l));
    }

    @Test
    public void testgetWarehouseByOid(){
        log.info(outcomingMapper.getWarehouseByOid(1l));
    }

    @Test
    public void testgetWaybillByOid(){
        log.info(outcomingMapper.getWaybillByOid(1l));
    }

    @Test void testshippingcompany(){
        log.info(outcomingMapper.getShippingcompanyByWbid(1l));
    }

    @Test void testgetOutcomingByOid(){
        log.info(outcomingMapper.getOutcomingByOid(1l));
    }

    @Test void testupdateOutcomingStatusByValue(){
        outcomingMapper.updateOutcomingStatusByValue("테스트",1l);
    }

    @Test void testupdateWaybillSidByWbidAndSid(){
        outcomingMapper.updateWaybillSidByWbidAndSid(1l,2l);
    }

    @Test void testdeleteOutcomingByOid(){
        outcomingMapper.deleteOutcomingByOid(2l);
    }


}
