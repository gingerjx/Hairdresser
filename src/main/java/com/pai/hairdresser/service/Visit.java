package com.pai.hairdresser.service;

import com.pai.hairdresser.model.MCalendar;
import com.pai.hairdresser.model.MDay;
import com.pai.hairdresser.model.MHour;
import com.pai.hairdresser.model.MUser;
import com.pai.hairdresser.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Visit {
  @Autowired
  private MCalendar mCalendar;
  public static String NOT_EXIST = "Cannot find chosen date";
  public static String C_EMPTY_CODE = "Please type authentication code to cancel your visit";
  public static String C_AVAILABLE_TERM = "This term is not booked";
  public static String C_INCORRECT_CODE = "Passed authentication code is incorrect";
  public static String C_SUCCESS = "Visit successfully canceled";

  public static String B_BOOKED = "This term is already booked";
  public static String B_LACK_OF_DATA = "Please fill form with your personal data";
  public static String B_SUCCESS = "Visit successfully booked";

  public String cancel(VisitInfo info) {
    int month = Integer.parseInt(info.getMonth());
    int day = Integer.parseInt(info.getDay());
    int hour = Integer.parseInt(info.getHour());
    String code = info.getCode();

    if (!mCalendar.exist(month, day, hour)) {
      return NOT_EXIST;
    }

    MDay mDay = mCalendar.getDate(month, day);
    MHour mHour = mDay.getHour(hour);

    if (!mHour.isBooked()) {
      return C_AVAILABLE_TERM;
    }

    if (code.equals("")) {
      return C_EMPTY_CODE;
    }

    if (!mHour.getAuthenticationCode().equals(code)) {
      return C_INCORRECT_CODE;
    }

    mCalendar.update(month, day, hour, false, "", new MUser("", "", ""));

    return C_SUCCESS;
  }

  public String book(VisitInfo info) {
    int month = Integer.parseInt(info.getMonth());
    int day = Integer.parseInt(info.getDay());
    int hour = Integer.parseInt(info.getHour());
    MUser user = info.getUser();

    if (!mCalendar.exist(month, day, hour)) {
      return NOT_EXIST;
    }

    MDay mDay = mCalendar.getDate(month, day);
    MHour mHour = mDay.getHour(hour);

    if (mHour.isBooked()) {
      return B_BOOKED;
    }

    if (user.getName().equals("") || user.getSurname().equals("") || user.getPhone().equals("")) {
      return B_LACK_OF_DATA;
    }

    info.setCode(CodeGenerator.generateCode(6));
    mCalendar.update(month, day, hour, true, info.getCode(), user);

    return B_SUCCESS;
  }

}
