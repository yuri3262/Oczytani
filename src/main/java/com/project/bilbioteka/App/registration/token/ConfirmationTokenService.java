package com.project.bilbioteka.App.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationToken;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationToken.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationToken.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationToken.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
