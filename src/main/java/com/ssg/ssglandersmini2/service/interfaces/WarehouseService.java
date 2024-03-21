package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;

import java.util.List;

public interface WarehouseService {
    void register(WarehouseDTO warehouseDTO);

    WarehouseDTO getOne(long wid);

    List<WarehouseDTO> getAll();

    void modify(WarehouseDTO warehouseDTO);

    void remove(long wid);


}