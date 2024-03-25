package com.ssg.ssglandersmini2.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private long bid;
    private String status;
    private long wid;
}
