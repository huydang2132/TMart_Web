package com.project.tmartweb.services.token;

import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.models.entities.Token;
import com.project.tmartweb.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService implements ITokenService {
    private final TokenRepository tokenRepository;

    @Override
    public List<Token> getAll() {
        return List.of();
    }

    @Override
    public Optional<Token> findById(String token) {
        return tokenRepository.findById(token);
    }

    @Override
    public Token getById(String token) {
        return findById(token).orElseThrow(() -> new NotFoundException("Token không tồn tại!", "Token not found!"));
    }
}
