package com.ssg.ssglandersmini2.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Warehouse {
    private Long id;
    private String address;
    private String warehouseType;
    private int capacity;
    private int usingCapacity;
    private String wname;

}
