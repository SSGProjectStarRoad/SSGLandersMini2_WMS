package com.ssg.ssglandersmini2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotNull
    private Long pid;
    @NotNull
    private String name;
    @NotNull
    private String firstcategory;
    private String secondcategory;
    private String thirdcategory;
    @NotNull
    private long palletperquantity;
    @NotNull
    private String type;
}
