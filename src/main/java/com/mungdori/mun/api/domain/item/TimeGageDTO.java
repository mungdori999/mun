package com.mungdori.mun.api.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TimeGageDTO {

    private Long id;

    private double gage;
    private LocalDateTime startGage;
    private LocalDateTime targetGage;

}
