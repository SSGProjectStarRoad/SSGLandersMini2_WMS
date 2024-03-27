package com.ssg.ssglandersmini2.domain;


import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    private long wid;
    private String warehousetype;
    private String address;
    private int capacity;
    private int usingcapacity;
    private String wname;
}