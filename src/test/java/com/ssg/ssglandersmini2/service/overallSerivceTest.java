package com.ssg.ssglandersmini2.service;

import com.ssg.ssglandersmini2.service.interfaces.OverallService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class overallSerivceTest {

    @Autowired
    private OverallService overallService;
}
