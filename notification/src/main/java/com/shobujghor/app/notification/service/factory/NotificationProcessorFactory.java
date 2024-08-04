package com.shobujghor.app.notification.service.factory;

import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationProcessorFactory {
    private final Map<NotificationMessageType, NotificationProcessorHelper> notificationProcessorHelperMap;
    private final ErrorHelperService errorHelperService;

    public NotificationProcessorFactory(List<NotificationProcessorHelper> notificationProcessorHelpers, ErrorHelperService errorHelperService) {
        this.notificationProcessorHelperMap = notificationProcessorHelpers.stream()
                .collect(Collectors.toMap(NotificationProcessorHelper::getProcessorType, Function.identity()));
        this.errorHelperService = errorHelperService;
    }

    public NotificationProcessorHelper getNotificationProcessorHelper(String messageType) {
        if (EnumUtils.isValidEnum(NotificationMessageType.class, messageType)) {
            return notificationProcessorHelperMap.get(NotificationMessageType.valueOf(messageType));
        } else {
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.INVALID_NOTIFICATION_MESSAGE);
        }
    }
}
