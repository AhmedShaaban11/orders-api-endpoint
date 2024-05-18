package com.system.services;

import com.system.apis.models.Notification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {
  private List<Notification> notificationsQueue;
  private Map<String, Notification> notificationTemplates;

  public NotificationService() {
    this.notificationsQueue = new ArrayList<>();
    this.notificationTemplates = new HashMap<>();
    addNotificationTemplate("placeOrderSuccess", new Notification(
      "Order Placed",
      "Dear ${customerName}^ Your order has been placed successfully.",
      List.of("en", "ar"),
      List.of("email", "sms")
    ));
    addNotificationTemplate("placeOrderFailure", new Notification(
      "Order Not Placed",
      "Dear ${customerName}^ Your order hasn't been placed due to insufficient balance or lack of products.",
      List.of("en", "ar"),
      List.of("email", "sms")
    ));
    addNotificationTemplate("shipOrderSuccess", new Notification(
      "Order Shipping",
      "Dear ${customerName}^ your order has been shipped successfully.",
      List.of("en", "ar"),
      List.of("email", "sms")
    ));
    addNotificationTemplate("shipOrderFailure", new Notification(
      "Order Not Shipped",
      "Dear ${customerName}^ your order hasn't been shipped due to some errors.",
      List.of("en", "ar"),
      List.of("email", "sms")
    ));
  }

  public void addNotificationTemplate(String name, Notification notification) {
    notificationTemplates.put(name, notification);
  }

  public boolean sendNotification(String name, Map<String, String> placeholders) {
    Notification notificationTemplate = notificationTemplates.get(name);
    if (notificationTemplate == null) { return false; }
    notificationsQueue.add(notificationTemplate.getNotificationOf(placeholders));
    return true;
  }

  public List<Notification> getNotificationQueue() {
    return notificationsQueue;
  }
}
