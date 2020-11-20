package com.pai.hairdresser.service;

import com.pai.hairdresser.model.MCalendar;
import com.pai.hairdresser.model.MDay;
import com.pai.hairdresser.model.MHour;
import com.pai.hairdresser.model.MMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CalendarInfo {
  @Autowired
  private MCalendar mCalendar;

  public ArrayList<Integer> getMonths() {
    ArrayList<Integer> months = new ArrayList<>();
    for (Map.Entry<Integer, MMonth> entry : mCalendar.getMonths().entrySet()) {
      months.add(entry.getKey());
    }

    return months;
  }

  public ArrayList<VisitInfo> getRecentVisits(int num, boolean isBooked) {
    if (num <= 0) {
      return null;
    }

    int added = 0;
    ArrayList<VisitInfo> bookedVisits = new ArrayList<>();

    HashMap<Integer, MMonth> months = mCalendar.getMonths();
    SortedSet<Integer> monthKeys = new TreeSet<>(months.keySet());
    for (Integer monthKey : monthKeys) {

      HashMap<Integer, MDay> days = months.get(monthKey).getDays();
      SortedSet<Integer> dayKeys = new TreeSet<>(days.keySet());
      for (Integer dayKey : dayKeys) {

        HashMap<Integer, MHour> hours = days.get(dayKey).getHours();
        SortedSet<Integer> hourKeys = new TreeSet<>(hours.keySet());
        for (Integer hourKey : hourKeys) {

          MHour mHour = hours.get(hourKey);
          if (!isOldDate(monthKey, dayKey, hourKey) && (mHour.isBooked() == isBooked)) {
            bookedVisits.add(new VisitInfo(monthKey, dayKey, hourKey, mHour.getAuthenticationCode(), mHour.getUser()));
            added += 1;
          }
          if (added >= num)
            return bookedVisits;
        }
      }
    }

    return bookedVisits;
  }

  public boolean isOldDate(int month, int day, int hour) {
    /* It is assumed that we working only on november and december months.. ble */
    if (month < 1 || month > 12 || day < 1 || day > 31 || hour < 1 || hour > 24) {
      return true;
    }

    LocalDateTime now = LocalDateTime.now();
    now = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), 0, 0);
    LocalDateTime date = LocalDateTime.of(now.getYear(), month, day, hour, 0, 0);

    return date.isBefore(now);
  }
}
