package com.ssg.ssglandersmini2.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class Incoming {
    private Long iid;
    private Long pid;
    private Long wid;
    private int quantity;
    private LocalDate date;
    private String status;
    private String approval;
}
