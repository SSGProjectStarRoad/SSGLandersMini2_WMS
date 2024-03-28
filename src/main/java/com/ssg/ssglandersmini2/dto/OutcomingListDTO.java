package com.ssg.ssglandersmini2.dto;


import lombok.*;

import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutcomingListDTO {

    private long oid;
    private long pid;
    private long wid;
    private String name;
    private long quantity;
    private String warehousetype;
    private LocalDate date;
    private String approval;
    private String status;
    private long wbid;

}
