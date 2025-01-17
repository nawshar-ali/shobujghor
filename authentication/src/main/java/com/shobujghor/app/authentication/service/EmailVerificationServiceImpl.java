package com.shobujghor.app.authentication.service;

import com.shobujghor.app.authentication.repository.dynamo.EmailVerificationTokenRepository;
import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.utility.constants.IdStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final UserInfoRepository userInfoRepository;

    @Override
    public String verifyEmail(String token) {
        return emailVerificationTokenRepository.getData(token)
                .filter(emailVerificationToken -> emailVerificationToken.getTokenExpiryDate().isAfter(LocalDateTime.now()))
                .map(emailVerificationToken -> {
                    userInfoRepository.getData(emailVerificationToken.getEmail())
                            .ifPresent(userInfo -> {
                                userInfo.setStatus(IdStatus.VERIFIED);
                                userInfoRepository.saveData(userInfo);
                            });

                    emailVerificationTokenRepository.deleteData(emailVerificationToken);
                    return "token verified successfully";
                })
                .orElseThrow(() -> new RuntimeException("Email verification failed"));
    }
}
