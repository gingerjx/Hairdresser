package com.pai.hairdresser.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class MHourTest {
  @Autowired
  private MCalendar mCalendar;

  @Test
  void loading() {
    MDay mDay = mCalendar.getMonth(12).getDay(1);

    assertNotNull(mDay);
    assertEquals(8, mDay.getHours().size());

    for (Map.Entry<Integer, MHour> entry : mDay.getHours().entrySet()) {
      int hour = entry.getKey();
      MHour mHour = entry.getValue();
      assertEquals(hour, mHour.getHour());
    }
  }
}