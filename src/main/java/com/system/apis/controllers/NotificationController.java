package com.system.apis.controllers;

import com.system.apis.models.Notification;
import com.system.services.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Queue;

@RestController
public class NotificationController {
  private NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("/notifications")
  public List<Notification> getNotifications() {
    return this.notificationService.getNotificationQueue();
  }
}
