package com.ssg.ssglandersmini2.domain;


import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Outcoming {
    private long oid;
    private long pid;
    private long wid;
    private long quantity;
    private LocalDate date;
    private String status;
    private String approval;
    private long wbid;
}
