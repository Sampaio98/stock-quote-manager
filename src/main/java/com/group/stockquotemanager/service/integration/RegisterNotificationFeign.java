package com.group.stockquotemanager.service.integration;

import com.group.stockquotemanager.dto.integration.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8080/notification", name = "RegisterNotificationFeign")
public interface RegisterNotificationFeign {

    @PostMapping
    NotificationDTO registerNotification(NotificationDTO notificationDTO);
}
