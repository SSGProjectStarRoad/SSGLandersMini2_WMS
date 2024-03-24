package com.ssg.ssglandersmini2.domain;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private int pid;
    private int wid;
    private int quantity;

}

