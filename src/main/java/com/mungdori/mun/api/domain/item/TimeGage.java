package com.mungdori.mun.api.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeGage {

    private Long id;
    private Long accountId;
    private Long itemId;
    private String currentGage;
    private String targetGage;


}
