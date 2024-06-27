package com.mungdori.mun.api.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CartDTO {

    private Long id;
    private Long accountId;
    private Long productId;

    private String productName;
    private String price;

}
