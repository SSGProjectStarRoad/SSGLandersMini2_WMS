package com.ssg.ssglandersmini2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomingDTO {
    private Long iid;
    private Long pid;
    private Long wid;
    private int quantity;
    private LocalDate date;
    private String status;
    private String approval;
}
