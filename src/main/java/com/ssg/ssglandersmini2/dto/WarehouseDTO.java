package com.ssg.ssglandersmini2.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class WarehouseDTO {
    private long wid;
    private String address;
    private String warehousetype;
    private int capacity;
    private int usingcapacity;
    private String wname;
}
