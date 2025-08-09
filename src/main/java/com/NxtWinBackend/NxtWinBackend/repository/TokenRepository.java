package com.NxtWinBackend.NxtWinBackend.repository;

import com.NxtWinBackend.NxtWinBackend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
