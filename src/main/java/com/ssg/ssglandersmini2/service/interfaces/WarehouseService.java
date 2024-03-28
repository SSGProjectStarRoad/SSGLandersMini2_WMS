package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;

import java.util.List;

public interface WarehouseService {
    void registerWarehouse(WarehouseDTO warehouseDTO);

    WarehouseDTO getOneWarehouse(long wid);

    List<WarehouseDTO> getAllWarehouse();
    PageResponseDTO<WarehouseDTO> listWarehouse(PageRequestDTO pageRequestDTO);

//    void modify(WarehouseDTO warehouseDTO);

    void removeWarehouse(long wid);

    void addWarehouseAndPopulateInventory(Warehouse warehouse);

    void updateWarehouseName(long warehouseId, String warehousetype, String address);


}