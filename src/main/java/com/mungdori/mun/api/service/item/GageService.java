package com.mungdori.mun.api.service.item;

import com.mungdori.mun.api.domain.item.TimeGage;
import com.mungdori.mun.api.domain.item.TimeGageDTO;
import com.mungdori.mun.api.repository.item.GageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class GageService {

    private final GageRepository gageRepository;

    public void setStartGage(Long userId, Long itemId) {
        TimeGage timeGage = new TimeGage();
        timeGage.setAccountId(userId);
        timeGage.setItemId(itemId);
        timeGage.setStartGage(LocalDateTime.now());
        timeGage.setTargetGage(LocalDateTime.now().plusDays(3));
        gageRepository.saveTimeGage(timeGage);
    }

    public TimeGageDTO getGage(Long userId) {
        TimeGage timeGage = gageRepository.findGage(userId);
        LocalDateTime startGage = timeGage.getStartGage();
        LocalDateTime targetGage = timeGage.getTargetGage();
        // 현재 시점 가져오기
        LocalDateTime currentGage = LocalDateTime.now();

        // 시작 날짜와 끝 날짜 사이의 전체 기간 (초 단위)
        long totalDurationInSeconds = ChronoUnit.SECONDS.between(startGage, targetGage);
        // 시작 날짜와 현재 시점 사이의 경과 기간 (초 단위)
        long elapsedDurationInSeconds = ChronoUnit.SECONDS.between(startGage, currentGage);
        // 현재 시점이 끝 날짜 이후라면 100%로 설정
        double gage;
        if (elapsedDurationInSeconds >= totalDurationInSeconds) {
            gage = 100.0;
        } else {
            // 경과 기간을 전체 기간으로 나누어 퍼센트 값 계산
            gage = (double) elapsedDurationInSeconds / totalDurationInSeconds * 100;
        }

        return new TimeGageDTO(timeGage.getId(), gage, timeGage.getStartGage(), timeGage.getTargetGage());
    }
}
