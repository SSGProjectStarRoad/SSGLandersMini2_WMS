package com.ssg.ssglandersmini2.mappers;

import com.ssg.ssglandersmini2.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testGetProductByPid(){
        Product product = productMapper.getProductByPid(1L);
        log.info(product.getName());

    }
}
