package com.pai.hairdresser.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class MCalendarTest {
  @Autowired
  MCalendar mCalendar;

  @Test
  void loading() {
    assertEquals(2, mCalendar.getMonths().size());

    assertNull(mCalendar.getMonth(-2));
    assertNull(mCalendar.getMonth(5));
    assertNull(mCalendar.getMonth(10));
    assertNull(mCalendar.getMonth(16));

    assertNotNull(mCalendar.getMonth(11));
    assertNotNull(mCalendar.getMonth(12));

    assertNull(mCalendar.getDate(10, 24));
    assertNull(mCalendar.getDate(5, 24));
    assertNull(mCalendar.getDate(-2, 2));
    assertNull(mCalendar.getDate(16, 1));

    assertNotNull(mCalendar.getDate(12, 1));
    assertNotNull(mCalendar.getDate(11, 2));
  }

  @Test
  void updateExistingTerm() {
    int month = 11;
    int day = 2;
    int hour = 10;
    MHour mHour = mCalendar.getDate(month, day).getHour(hour);

    assertTrue(mHour.isBooked());
    assertEquals("BGH7#O", mHour.getAuthenticationCode());
    MUser user = mHour.getUser();
    assertNotNull(user);
    assertEquals("Joe", user.getName());
    assertEquals("Solberg", user.getSurname());
    assertEquals("782-321-2192", user.getPhone());

    mCalendar.update(month, day, hour, false, "", new MUser("", "", ""));

    assertEquals(false, mHour.isBooked());
    assertEquals("", mHour.getAuthenticationCode());
    user = mHour.getUser();
    assertNotNull(user);
    assertEquals("", user.getName());
    assertEquals("", user.getSurname());
    assertEquals("", user.getPhone());

    // restore previous values. To check if saving to xml works
    // comment it and go to 'src/test/resources/calendar.xml'
    mCalendar.update(month, day, hour, true, "BGH7#O", new MUser("Joe", "Solberg", "782-321-2192"));
  }

  @Test
  void updateNoneExistingTerm() {
    int month = -3;
    int day = 109;
    int hour = 0;

    // Should do nothing
    mCalendar.update(month, day, hour, false, "", null);
  }

  @Test
  void exist() {
    assertTrue(mCalendar.exist(12, 1, 11));
    assertTrue(mCalendar.exist(12, 2, 11));
    assertTrue(mCalendar.exist(11, 3, 14));
    assertTrue(mCalendar.exist(11, 1, 15));

    assertFalse(mCalendar.exist(9, 1, 11));
    assertFalse(mCalendar.exist(-5, 0, 11));
    assertFalse(mCalendar.exist(10, 3, 9));
    assertFalse(mCalendar.exist(11, 0, 15));
  }
}