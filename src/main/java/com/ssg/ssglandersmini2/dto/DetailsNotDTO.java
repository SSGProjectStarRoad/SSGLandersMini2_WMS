package com.ssg.ssglandersmini2.dto;


import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailsNotDTO {
    private String name;
    private long quantity;
    private String warehousetype;
    private String firstcategory;
    private String secondcategory;
    private String thirdcategory;
}
