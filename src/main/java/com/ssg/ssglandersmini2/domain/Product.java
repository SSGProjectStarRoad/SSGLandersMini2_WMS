package com.ssg.ssglandersmini2.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
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
