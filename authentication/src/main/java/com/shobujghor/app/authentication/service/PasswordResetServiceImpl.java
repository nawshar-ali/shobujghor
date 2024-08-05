package com.shobujghor.app.authentication.service;

import com.google.gson.Gson;
import com.shobujghor.app.authentication.repository.dynamo.PasswordResetInfoRepository;
import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.authentication.util.PasswordUtil;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.dto.NotificationDto;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.models.PasswordResetInfo;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserInfoRepository userInfoRepository;
    private final ErrorHelperService errorHelperService;
    private final PasswordResetInfoRepository passwordResetInfoRepository;
    private final SqsTemplate sqsTemplate;
    private final Gson gson;

    @Value("${notification.queue}")
    private String notificationQueue;
    private static final String PASSWORD_CHANGE_ENDPOINT = "/user/change-password?token=";

    @Override
    public void resetUserPassword(PasswordResetRequest request, HttpServletRequest httpServletRequest) {
        var userInfoOpt = userInfoRepository.getData(request.getEmail());

        if (userInfoOpt.isEmpty()) {
            log.error("User: {} not found in DB", request.getEmail());
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.USER_NOT_FOUND);
        }

        var passwordResetInfo = getPasswordResetInfo(request, httpServletRequest);
        passwordResetInfoRepository.saveData(passwordResetInfo);

        var notificationDto = NotificationDto.builder()
                .messageType(NotificationMessageType.PASSWORD_RESET_EMAIL)
                .passwordResetLink(passwordResetInfo.getPasswordResetLink())
                .userEmail(passwordResetInfo.getEmail())
                .build();

        var sqsPayload = gson.toJson(notificationDto);

        try {
            sqsTemplate.sendAsync(sqsSendOptions -> sqsSendOptions
                    .queue(notificationQueue)
                    .payload(sqsPayload)
                    .messageGroupId(passwordResetInfo.getToken()));
        } catch (Exception e) {
            log.error("User: {} | Failed to publish event in notification queue for password reset", request.getEmail(), e);
            throw errorHelperService.buildExceptionFromCode(e.getMessage());
        }
    }

    @Override
    public String validatePasswordResetToken(String token) {
        var passwordResetInfoOpt = passwordResetInfoRepository.getData(token);

        if (passwordResetInfoOpt.isEmpty()) {
            log.error("Invalid password reset token");
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.INVALID_PASSWORD_RESET_TOKEN);
        }

        return passwordResetInfoOpt.get().getEmail();
    }

    @Override
    public boolean savePassword(SavePasswordRequest request) {
        var userOpt = userInfoRepository.getData(request.getEmail());

        if (userOpt.isEmpty()) {
            log.error("savePassword | User: {} not found in DB", request.getEmail());
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.USER_NOT_FOUND);
        }

        var user = userOpt.get();

        user.setPassword(PasswordUtil.getEncryptedPassword(request.getPassword()));
        userInfoRepository.saveData(user);

        return true;
    }

    private PasswordResetInfo getPasswordResetInfo(PasswordResetRequest request, HttpServletRequest httpServletRequest) {
        var token = UUID.randomUUID().toString();

        String passwordResetLink = httpServletRequest.getLocale() + PASSWORD_CHANGE_ENDPOINT + token;

        return PasswordResetInfo.builder()
                .email(request.getEmail())
                .token(token)
                .passwordResetLink(passwordResetLink)
                .build();
    }
}
