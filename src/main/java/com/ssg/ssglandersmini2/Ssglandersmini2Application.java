package com.ssg.ssglandersmini2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ssg.ssglandersmini2.mappers")
@SpringBootApplication
public class Ssglandersmini2Application {
    public static void main(String[] args) {
        SpringApplication.run(Ssglandersmini2Application.class, args);
    }

}
