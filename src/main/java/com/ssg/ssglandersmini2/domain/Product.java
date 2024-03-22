package com.ssg.ssglandersmini2.domain;


import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long pid;
    private String name;
    private String firstcategory;
    private String secondcategory;
    private String thirdcategory;
    private long palletperquantity;
    private String type;

}
