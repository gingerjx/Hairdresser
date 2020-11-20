package com.pai.hairdresser.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class CalendarInfoTest {
  @Autowired
  CalendarInfo calendarInfo;

  @Test
  void numberOfMonths() {
    ArrayList<Integer> months = calendarInfo.getMonths();
    assertNotNull(months);
    assertEquals(2, months.size());
    assertEquals(11, months.get(0));
    assertEquals(12, months.get(1));
  }

  @Test
  void getAvailableVisits() {
    /* It depends on local datetime, so it have to be adjusted everytime */
    /*
    ArrayList<VisitInfo> visits = calendarInfo.getRecentVisits(1, false);
    assertNotNull(visits);
    assertEquals(1, visits.size());

    VisitInfo visit = visits.get(0);
    assertNotNull(visit);
    assertEquals("1", visit.getDay());
    assertEquals("13", visit.getHour());

    visits = calendarInfo.getRecentVisits(3, false);
    assertNotNull(visits);
    assertEquals(3, visits.size());

    visit = visits.get(2);
    assertNotNull(visit);
    assertEquals("1", visit.getDay());
    assertEquals("16", visit.getHour());
    */
  }

  @Test
  void getBookedVisits() {
    /* It depends on local datetime, so it have to be adjusted everytime */
    /*
    ArrayList<VisitInfo> visits = calendarInfo.getRecentVisits(1, true);
    assertNotNull(visits);
    assertEquals(1, visits.size());

    VisitInfo visit = visits.get(0);
    assertNotNull(visit);
    assertEquals("1", visit.getDay());
    assertEquals("10", visit.getHour());
    assertEquals("511XYM", visit.getCode());
    assertEquals("Joe", visit.getUser().getName());
    assertEquals("Solberg", visit.getUser().getSurname());
    assertEquals("369-201-1098", visit.getUser().getPhone());

    visits = calendarInfo.getRecentVisits(3, true);
    assertNotNull(visits);
    assertEquals(3, visits.size());

    visit = visits.get(2);
    assertNotNull(visit);
    assertEquals("1", visit.getDay());
    assertEquals("12", visit.getHour());
    assertEquals("PZV4HW", visit.getCode());
    assertEquals("Ty", visit.getUser().getName());
    assertEquals("Sellon", visit.getUser().getSurname());
    assertEquals("102-441-9745", visit.getUser().getPhone());
     */
  }

  @Test
  void isOldDate() {
    LocalDateTime now = LocalDateTime.now();
    int month = now.getMonthValue();
    int day = now.getDayOfMonth();
    int hour = now.getHour();

    assertFalse(calendarInfo.isOldDate(month, day, hour));

    assertTrue(calendarInfo.isOldDate(-1, 10, 10));
    assertTrue(calendarInfo.isOldDate(1, 10, 25));
    assertTrue(calendarInfo.isOldDate(7, 13, 10));

    if (month + 1 < 13)
      month++;
    assertFalse(calendarInfo.isOldDate(month, day, hour));
    month = now.getMonthValue();

    if (day + 1 < 32)
      day++;
    assertFalse(calendarInfo.isOldDate(month, day, hour));
    day = now.getDayOfMonth();

    if (hour + 1 < 25)
      hour++;
    assertFalse(calendarInfo.isOldDate(month, day, hour));
  }
}