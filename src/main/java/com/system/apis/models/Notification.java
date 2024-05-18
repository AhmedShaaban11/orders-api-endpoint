package com.system.apis.models;

import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

public class Notification {
  private String subject;
  private String content;
  private List<String> languages;
  private List<String> channels;

  public Notification() {
  }

  public Notification(String subject, String content, List<String> languages, List<String> channels) {
    this.subject = subject;
    this.content = content;
    this.languages = languages;
    this.channels = channels;
  }

  public Notification getNotificationOf(Map<String, String> placeholders) {
    Notification notification = new Notification(subject, content, languages, channels);
    StringBuilder stringBuilder = new StringBuilder(content);
    for (var entry : placeholders.entrySet()) {
      String placeholder = "${" + entry.getKey() + "}^";
      int idx = stringBuilder.indexOf(placeholder);
      if (idx == -1) { continue; }
      stringBuilder.replace(idx, idx + placeholder.length(), entry.getValue());
    }
    String filledContent = stringBuilder.toString();
    return new Notification(subject, filledContent, languages, channels);
  }

  public String getSubject() {
    return subject;
  }

  public String getContent() {
    return content;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public List<String> getChannels() {
    return channels;
  }
}
