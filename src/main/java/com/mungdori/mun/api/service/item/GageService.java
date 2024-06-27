package com.mungdori.mun.api.service.item;

import com.mungdori.mun.api.domain.item.TimeGage;
import com.mungdori.mun.api.repository.item.GageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GageService {

    private final GageRepository gageRepository;
    public void setStartGage(Long userId, Long itemId) {
        TimeGage timeGage = new TimeGage();
        timeGage.setAccountId(userId);
        timeGage.setItemId(itemId);
        int totalTime = 60*60*24*3;
        timeGage.setTargetGage(String.valueOf(totalTime));
        timeGage.setCurrentGage("0");
        gageRepository.saveTimeGage(timeGage);
    }
}
