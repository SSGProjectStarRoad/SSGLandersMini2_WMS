package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;
import com.ssg.ssglandersmini2.mappers.WarehouseMapper;
import com.ssg.ssglandersmini2.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;
    @Override
    public void register(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        warehouseMapper.insert(warehouse);
    }

    @Override
    public WarehouseDTO getOne(long wid) {
        WarehouseDTO warehouseDTO = modelMapper.map(warehouseMapper.getOne(wid),WarehouseDTO.class);
        return warehouseDTO;
    }

    @Override
    public List<WarehouseDTO> getAll() {
        List<WarehouseDTO> warehouseDTOS = warehouseMapper.getAll().stream()
                .map(entity->modelMapper.map(entity,WarehouseDTO.class))
                .collect(Collectors.toList());
        return warehouseDTOS;
    }

    @Override
    public void modify(WarehouseDTO warehouseDTO) {

    }

    @Override
    public void remove(long wid) {

    }


}
