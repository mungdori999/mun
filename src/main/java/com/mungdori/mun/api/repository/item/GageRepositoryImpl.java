package com.mungdori.mun.api.repository.item;

import com.mungdori.mun.api.domain.item.TimeGage;
import com.mungdori.mun.api.mapper.GageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GageRepositoryImpl implements GageRepository{

    private final GageMapper gageMapper;
    @Override
    public void saveTimeGage(TimeGage timeGage) {
        gageMapper.insertTimeGage(timeGage);
    }

    @Override
    public TimeGage findGage(Long userId) {
        return gageMapper.selectTimeGage(userId);
    }
}
