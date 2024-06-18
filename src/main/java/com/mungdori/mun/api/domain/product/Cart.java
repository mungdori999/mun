package com.mungdori.mun.api.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private Long id;
    private Long accountId;
    private Long productId;
}
