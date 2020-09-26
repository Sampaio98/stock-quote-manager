package com.group.stockquotemanager.config;

import com.group.stockquotemanager.dto.integration.NotificationDTO;
import com.group.stockquotemanager.service.integration.RegisterNotificationFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.NetworkInterface;
import java.net.SocketException;

@Component
public class RegisterConfig {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RegisterNotificationFeign registerNotificationFeign;

    @Bean
    public void populateData() throws SocketException {
        String hostAddress = NetworkInterface.getNetworkInterfaces().nextElement().getInetAddresses().nextElement().getHostAddress();

        NotificationDTO notificationDTO = NotificationDTO.builder()
                .port(port)
                .host(hostAddress)
                .build();
        registerNotificationFeign.registerNotification(notificationDTO);
    }
}
