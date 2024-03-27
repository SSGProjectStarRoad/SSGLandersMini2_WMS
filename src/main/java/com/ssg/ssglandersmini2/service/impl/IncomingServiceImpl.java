package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.domain.Product;
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

    }

    @Override
    public IncomingListDTO getOne(Long iid) {
        return null;
    }

    @Override
    public void remove(Long pid) {

    }

    @Override
    public void modify(IncomingDTO incomingDTO) {

    }

    @Override
    public PageResponseDTO<IncomingListDTO> getIncomingList(PageRequestDTO pageRequestDTO) {
        return null;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public PageResponseDTO<IncomingListDTO> getIncomingList(PageRequestDTO pageRequestDTO) {
//        List<Incoming> list = incomingMapper.selectIncomingList(pageRequestDTO);
//
//        List<IncomingListDTO> dtoList = list.stream()
//                .map(vo -> modelMapper.map(vo, IncomingListDTO.class)).collect(Collectors.toList());
//
//        dtoList.forEach(dto -> modelMapper.map(
//                productMapper.getProductByPid(dto.getPid()), dto));
//
//
//        int total = incomingMapper.getCount(pageRequestDTO);
//
//
//        return PageResponseDTO.<IncomingListDTO>All()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total(total)
//                .build();
//    }

}

