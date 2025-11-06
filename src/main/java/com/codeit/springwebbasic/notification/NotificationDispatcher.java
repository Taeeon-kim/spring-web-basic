package com.codeit.springwebbasic.notification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationDispatcher {

    private final List<NotificationService> allServices;

    // 컬렉션 타입 빈 주입
    public NotificationDispatcher(List<NotificationService> allServices) {
        this.allServices = allServices;
        System.out.println("등록된 알림 서비스: " + allServices.size() + "개");
    }

    public void broadcast(String recipient, String message) {
        allServices.forEach(service -> service.sendNotification(recipient, message));
    }

}
