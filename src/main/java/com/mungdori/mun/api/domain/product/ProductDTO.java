package com.mungdori.mun.api.domain.product;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private String price;
    private List<String> tags;
    private String imgUrl;
}
