package com.ssg.ssglandersmini2.domain;

import lombok.*;

import java.time.LocalDate;
import java.sql.Blob;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Waybill {

    private long wbid;
    private String destination;
    private LocalDate date;
    private long sid;
    private Blob qr; // Blob 데이터를 위한 필드
}
