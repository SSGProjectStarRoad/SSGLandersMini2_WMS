package com.ssg.ssglandersmini2.dto;

import com.ssg.ssglandersmini2.domain.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomingListDTO {
    private Long iid;
    private Long pid;
    private Long wid;
    private int quantity;
    private LocalDate date;
    private String status;
    private String approval;

    private String name;
    private String type;
    private long palletperquantity;
    private String firstcategory;
    private String secondcategory;
    private String thirdcategory;


}
