package com.group.stockquotemanager.config;

import com.group.stockquotemanager.dto.integration.NotificationDTO;
import com.group.stockquotemanager.service.integration.RegisterNotificationFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.SocketException;

@Slf4j
@Component
public class RegisterConfig {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private RegisterNotificationFeign registerNotificationFeign;

    @Bean
    public void populateData() throws SocketException {
        NotificationDTO notificationDTO = NotificationDTO.builder()
                .port(port)
                .host("localhost")
                .build();
        log.info("Sending request to register api");
        registerNotificationFeign.registerNotification(notificationDTO);
    }
}
