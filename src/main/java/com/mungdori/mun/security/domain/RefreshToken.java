package com.mungdori.mun.security.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@RedisHash(value = "refreshToken", timeToLive = 86400)
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String refreshToken;
    private Long id;


    public RefreshToken(String refreshToken, Long id) {
        this.refreshToken = refreshToken;
        this.id = id;
    }
}
