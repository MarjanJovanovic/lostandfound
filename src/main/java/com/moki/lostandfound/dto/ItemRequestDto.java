package com.moki.lostandfound.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemRequestDto {
    private Long cityId;
    private Long categoryId;
    private Boolean isLost;
    private String imageFileName;
    private String name;
    private String description;

}
