package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;
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

    @Override
    public void register(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        warehouseMapper.insert(warehouse);
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


}
