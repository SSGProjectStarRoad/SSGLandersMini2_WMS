package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.dto.*;
import com.ssg.ssglandersmini2.mappers.IncomingMapper;
import com.ssg.ssglandersmini2.mappers.ProductMapper;
import com.ssg.ssglandersmini2.service.interfaces.IncomingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class IncomingServiceImpl implements IncomingService {

    private final ModelMapper modelMapper;
    private final IncomingMapper incomingMapper;
    private final ProductMapper productMapper;

    @Override
    public void register(IncomingDTO incomingDTO) {
        Incoming incoming = modelMapper.
                map(incomingDTO, Incoming.class);
        incomingMapper.insertOne(incoming);
    }



    @Override
    public void remove(Long iid) {
        incomingMapper.deleteOne(iid);
    }

    @Override
    public void modify(IncomingDTO incomingDTO) {
        Incoming incoming = modelMapper.map(incomingDTO, Incoming.class);
        incomingMapper.updateOne(incoming);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<IncomingListDTO> getIncomingList(PageRequestDTO pageRequestDTO) {
        List<Incoming> list = incomingMapper.selectIncomingList(pageRequestDTO);

        List<IncomingListDTO> dtoList = list.stream()
                .map(vo -> modelMapper.map(vo, IncomingListDTO.class)).collect(Collectors.toList());

        dtoList.forEach(dto -> modelMapper.map(
                productMapper.getProductByPid(dto.getPid()), dto));


        int total = incomingMapper.getCount(pageRequestDTO);


        return PageResponseDTO.<IncomingListDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    @Override
    public PageResponseDTO<IncomingListDTO> getIncomingNotApprovalList(PageRequestDTO pageRequestDTO) {
        List<Incoming> list = incomingMapper.selectIncomingList(pageRequestDTO);

        List<IncomingListDTO> dtoList = list.stream()
                .map(vo -> modelMapper.map(vo, IncomingListDTO.class)).collect(Collectors.toList());

        dtoList.forEach(dto -> modelMapper.map(
                productMapper.getProductByPid(dto.getPid()), dto));


        int total = incomingMapper.getCount(pageRequestDTO);


        return PageResponseDTO.<IncomingListDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    @Override
    public List<Map<String,String>> getWarehouseListByType(String type) {
        return incomingMapper.findListByWarehouseType(type);
    }

    @Override
    public Long getWidByWarehouseName(String wname) {
        return incomingMapper.findWidByWarehouseName(wname);
    }

    @Override
    public void modifyApprovalByIid(Long iid) {
        incomingMapper.updateApprovalByIid(iid);
    }

    @Override
    public void changeStatus(Long iid) {
        incomingMapper.updateStatus(iid);
    }


    @Override
    public boolean compareCapacity(Long wid, int quantity) {
        if (quantity <= incomingMapper.getWarehouseQuantity(wid)) {
            return true;
        }else return false;
    }

}

