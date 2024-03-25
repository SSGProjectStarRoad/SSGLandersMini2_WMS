package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Product;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;

import java.util.List;

public interface ProductMapper {
    List<Product> selectProductList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    //    void insert(Product product);
    Product getProductByPid(Long pid);
}
