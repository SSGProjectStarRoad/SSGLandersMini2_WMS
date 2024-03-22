package com.ssg.ssglandersmini2.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseCityDTO {

    @Size(min = 2,max = 3)
    private String city;
    @PositiveOrZero
    private long totalcapacity;
    @PositiveOrZero
    private long totalusingcapacity;
}
