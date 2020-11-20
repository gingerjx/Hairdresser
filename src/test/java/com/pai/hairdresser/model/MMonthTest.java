package com.pai.hairdresser.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class MMonthTest {
  @Autowired
  MCalendar mCalendar;

  @Test
  void loading() {
    for (Map.Entry<Integer, MMonth> entry : mCalendar.getMonths().entrySet()) {
      int monthNumber = entry.getKey();
      MMonth mMonth = entry.getValue();
      assertEquals(monthNumber, mMonth.getMonth());
    }

    MMonth november = mCalendar.getMonth(11);
    MMonth december = mCalendar.getMonth(12);

    assertNotNull(november);
    assertNotNull(december);

    assertEquals(3, november.getDays().size());
    assertEquals(2, december.getDays().size());

    assertNotNull(november.getDay(1));
    assertNotNull(november.getDay(2));
    assertNotNull(november.getDay(3));
    assertNotNull(december.getDay(1));
    assertNotNull(december.getDay(2));

    assertNull(november.getDay(-2));
    assertNull(november.getDay(4));
    assertNull(december.getDay(0));
    assertNull(december.getDay(36));
  }
}