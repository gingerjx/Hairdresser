package com.pai.hairdresser.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class MDayTest {
  @Autowired
  MCalendar mCalendar;

  @Test
  void loading() {
    MMonth december = mCalendar.getMonth(12);

    assertNotNull(december);

    assertEquals(2, december.getDays().size());

    for (Map.Entry<Integer, MDay> entry : december.getDays().entrySet()) {
      int dayNumber = entry.getKey();
      MDay mDay = entry.getValue();
      assertEquals(dayNumber, mDay.getDay());
    }

    // Check only first loaded day
    MDay mDay = december.getDay(1);

    assertEquals(8, mDay.getHours().size());

    assertNotNull(mDay.getHour(11));
    assertNotNull(mDay.getHour(15));
    assertNotNull(mDay.getHour(17));

    assertNull(mDay.getHour(3));
    assertNull(mDay.getHour(-4));
    assertNull(mDay.getHour(0));
    assertNull(mDay.getHour(26));
  }
}