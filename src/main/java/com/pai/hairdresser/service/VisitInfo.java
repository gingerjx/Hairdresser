package com.pai.hairdresser.service;

import com.pai.hairdresser.model.MUser;

public class VisitInfo {
  private String month;
  private String day;
  private String hour;
  private String code;
  private MUser user;

  public VisitInfo() {}

  public VisitInfo(int month, int day, int hour, String code, MUser user) {
    this.month = Integer.toString(month);
    this.day = Integer.toString(day);
    this.hour = Integer.toString(hour);
    this.code = code;
    this.user = user;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getHour() {
    return hour;
  }

  public void setHour(String hour) {
    this.hour = hour;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public MUser getUser() {
    return user;
  }

  public void setUser(MUser user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "VisitInfo{" +
            "month='" + month + '\'' +
            ", day='" + day + '\'' +
            ", hour='" + hour + '\'' +
            ", code='" + code + '\'' +
            ", user=" + user +
            '}';
  }
}
