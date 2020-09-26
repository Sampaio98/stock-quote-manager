package com.group.stockquotemanager.service.integration;

import com.group.stockquotemanager.dto.integration.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://172.17.0.3:8080/stock", name = "RegisterNotificationFeign")
public interface RegisterNotificationFeign {

    @PostMapping
    NotificationDTO registerNotification(NotificationDTO notificationDTO);
}
