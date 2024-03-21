package com.ssg.ssglandersmini2;

import com.ssg.ssglandersmini2.dto.WarehouseDTO;
import com.ssg.ssglandersmini2.service.interfaces.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ssglandersmini2ApplicationTests {
    @Autowired
    WarehouseService warehouseService;
    @Test
    void contextLoads() {
    }

    @Test
    void testRegisterWarehouse(){
        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
                .warehouseType("테스트타입")
                .address("테스트주소")
                .capacity(1234).build();
        warehouseService.register(warehouseDTO);
    }

}
