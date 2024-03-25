package com.ssg.ssglandersmini2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class MonthlyDTO {
    private int year;
    private int[] incoming;
    private int[] outcoming;

}
