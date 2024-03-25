package com.ssg.ssglandersmini2.service.interfaces;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.ProductDTO;

public interface ProductService {
    void register(ProductDTO productDTO);
    ProductDTO getOne(Long pid);

    void remove(Long pid);
    void modify(ProductDTO productDTO);
    PageResponseDTO<ProductDTO> getProductList(PageRequestDTO pageRequestDTO);
}
