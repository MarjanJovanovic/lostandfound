package com.moki.lostandfound.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isLost;
    private String category;
    private String city;

    private List<ImageResponseDto> images;
}
