package com.mungdori.mun.api.domain.item;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeGage {

    private Long id;
    private Long accountId;
    private Long itemId;
    private LocalDateTime startGage;
    private LocalDateTime targetGage;


}
