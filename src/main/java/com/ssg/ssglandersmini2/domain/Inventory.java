package com.ssg.ssglandersmini2.domain;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private long bid;
    private String status;
    private long wid;
}
