package com.mungdori.mun.api.domain.product;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String price;
    private List<String> tags;
    private String imgUrl;

}
