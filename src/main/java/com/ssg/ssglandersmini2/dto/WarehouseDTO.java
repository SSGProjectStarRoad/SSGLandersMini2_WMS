package com.ssg.ssglandersmini2.dto;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    private Long id;
    private String address;
    private String warehouseType;
    private int capacity;
    private int usingCapacity;
    private String wname;
}
