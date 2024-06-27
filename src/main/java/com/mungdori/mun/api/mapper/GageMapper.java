package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.item.TimeGage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GageMapper {

    void insertTimeGage(TimeGage timeGage);
}
