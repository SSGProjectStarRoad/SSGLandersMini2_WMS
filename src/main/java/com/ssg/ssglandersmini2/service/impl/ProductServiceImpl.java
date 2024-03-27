package com.ssg.ssglandersmini2.service.impl;

import com.ssg.ssglandersmini2.domain.Product;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.ProductDTO;
import com.ssg.ssglandersmini2.mappers.ProductMapper;
import com.ssg.ssglandersmini2.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;


    @Override
    public void register(ProductDTO productDTO) {

    }

    @Override
    public ProductDTO getOne(Long pid) {
        return null;
    }

    @Override
    public void remove(Long pid) {

    }

    @Override
    public void modify(ProductDTO productDTO) {

    }

    @Override
    public PageResponseDTO<ProductDTO> getProductList(PageRequestDTO pageRequestDTO) {
        return null;
    }

//    @Override
//    public PageResponseDTO<ProductDTO> getProductList(PageRequestDTO pageRequestDTO) {
//        List<Product> list = productMapper.selectProductList(pageRequestDTO);
//        List<ProductDTO> dtoList = list.stream()
//                .map(vo->modelMapper.map(vo, ProductDTO.class)).toList();
//
//        int total = productMapper.getCount(pageRequestDTO);
//
//
//        return PageResponseDTO.<ProductDTO>All()
//                .dtoList(dtoList)
//                .total(total)
//                .pageRequestDTO(pageRequestDTO)
//                .build();
//    }
}
