package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Inventory;
import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;
import com.ssg.ssglandersmini2.mappers.InventoryMapper;
import com.ssg.ssglandersmini2.mappers.WarehouseMapper;
import com.ssg.ssglandersmini2.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;
    private final InventoryMapper inventoryMapper;

    @Override
    public void register(WarehouseDTO warehouseDTO) {
        //DTO를 VO로 변환해서 db에 매퍼.insert()
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        warehouseMapper.insertWarehouse(warehouse);
//        인벤토리 추가 메소드, 가장 최근에 등록된 wid로.
        addWarehouseAndPopulateInventory(warehouseMapper.getMaxWid());
//        wid에 따른 창고명 업데이트
        updateWarehouseName(warehouse.getWid(), warehouseDTO.getWarehousetype(), warehouseDTO.getAddress());
    }

    @Override
    public WarehouseDTO getOne(long wid) {
        WarehouseDTO warehouseDTO = modelMapper.map(warehouseMapper.getOne(wid), WarehouseDTO.class);
        return warehouseDTO;
    }


    @Override
    public List<WarehouseDTO> getAll() {
        List<WarehouseDTO> warehouseDTOS = warehouseMapper.getAll().stream()
                .map(entity -> modelMapper.map(entity, WarehouseDTO.class))
                .collect(Collectors.toList());
        return warehouseDTOS;
    }

    @Override
    public PageResponseDTO<WarehouseDTO> list(PageRequestDTO pageRequestDTO) {


        int skip = (pageRequestDTO.getPage() -1)* pageRequestDTO.getSize();
        pageRequestDTO.getSkip();
        // Warehouse 리스트
        List<Warehouse> voList = warehouseMapper.selectList(pageRequestDTO);

        int totalCount = warehouseMapper.getCount(pageRequestDTO);

        // dto리스트에 warehouse 내용 넣음
        List<WarehouseDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, WarehouseDTO.class))
                .collect(Collectors.toList());

        // dto리스트에 wid, pid 이용해서 수량 가져오기 xx 잘못함
//        dtoList.forEach(dto -> dto.setQuantity(outcomingMapper.getStockQuantityByWidAndPid(dto.getWid(),dto.getPid())));

        PageResponseDTO<WarehouseDTO> pageResponseDTO = PageResponseDTO.<WarehouseDTO>withAll()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public void modify(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        warehouseMapper.update(warehouse);

    }

    @Override
    public void remove(long wid) {
        warehouseMapper.delete(wid);

    }
    public void addWarehouseAndPopulateInventory(Warehouse warehouse) {
//        창고의 wid 가져오기
        Long warehouseId = warehouse.getWid();
//        창고의 capacity 가져오기
        int capacity = warehouse.getCapacity();
        // capacity만큼 Inventory 객체 생성 및 저장
        for (int i = 1; i <= capacity; i++) {
            Inventory inventory = new Inventory();
            inventory.setBid(i);
            inventory.setWid(warehouseId);
            inventoryMapper.insertInventory(inventory);
        }
    }
    @Override
    public void updateWarehouseName(long wid, String warehousetype, String address) {
        // 창고 이름 생성 로직(주소의 앞 두글자, 타입, wid)
        String wname = address.substring(0, 2) + " " + warehousetype + " " + wid;
        // 창고 이름 업데이트
        warehouseMapper.updateWname(wid, wname);
    }

}
