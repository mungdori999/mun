package com.mungdori.mun.security.jwt;

import com.mungdori.mun.security.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    Optional<RefreshToken> findByAuthId(String authId);
}
