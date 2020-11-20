package com.pai.hairdresser.model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MHour {
  private final Integer hour;
  private Boolean booked;
  private MUser user;
  private String authenticationCode;
  private final Element element;

  public MHour(Element element, Integer hour, MUser user, String booked, String authenticationCode) {
    this.hour = hour;
    this.booked = Boolean.parseBoolean(booked);
    this.user = user;
    this.authenticationCode = authenticationCode;
    this.element = element;
  }

  public static MHour load(Element hourElement, Integer id) {
    String booked = hourElement.getElementsByTagName("booked").item(0).getTextContent();
    String authenticationCode = hourElement.getElementsByTagName("authenticationCode").item(0).getTextContent();
    String name = hourElement.getElementsByTagName("name").item(0).getTextContent();
    String surname = hourElement.getElementsByTagName("surname").item(0).getTextContent();
    String phone = hourElement.getElementsByTagName("phone").item(0).getTextContent();

    MUser user = new MUser(name, surname, phone);
    MHour mHour = new MHour(hourElement, id, user, booked, authenticationCode);

    return mHour;
  }

  public MUser getUser() {
    return user;
  }

  public Boolean isBooked() {
    return booked;
  }

  public String getAuthenticationCode() {
    return authenticationCode;
  }

  public Integer getHour() {
    return hour;
  }

  public void setUser(MUser user) {
    Node name = element.getElementsByTagName("name").item(0);
    name.setTextContent(user.getName());

    Node surname = element.getElementsByTagName("surname").item(0);
    surname.setTextContent(user.getSurname());

    Node phone = element.getElementsByTagName("phone").item(0);
    phone.setTextContent(user.getPhone());

    this.user = user;
  }

  public void setBooked(Boolean booked) {
    Node codeNode = element.getElementsByTagName("booked").item(0);
    codeNode.setTextContent(booked.toString());
    this.booked = booked;
  }

  public void setAuthenticationCode(String authenticationCode) {
    Node codeNode = element.getElementsByTagName("authenticationCode").item(0);
    codeNode.setTextContent(authenticationCode);
    this.authenticationCode = authenticationCode;
  }

  @Override
  public String toString() {
    return " Hour(" + hour + "){" +
            " booked=" + booked +
            " authenticationCode='" + authenticationCode + '\'' +
            "} ";
  }
}
