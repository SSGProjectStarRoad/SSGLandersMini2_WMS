package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.dto.WarehouseDTO;
import com.ssg.ssglandersmini2.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    @GetMapping("/register")
    public void register(){
        log.info("warehouse register doing...");
    }
    @PostMapping("/register")
    public void registerPost(WarehouseDTO warehouseDTO){
        log.info("warehouse register Post doing...");
        warehouseService.register(warehouseDTO);

    }
}
