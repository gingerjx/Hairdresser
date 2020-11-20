package com.pai.hairdresser.service;

import com.pai.hairdresser.model.MUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class VisitTest {
  @Autowired
  Visit visit;

  @Test
  void cancel() {
    VisitInfo info = new VisitInfo(-11, 1, 10, "", null);
    String result = visit.cancel(info);
    assertEquals(result, Visit.NOT_EXIST);

    info = new VisitInfo(11, 1, 13, "", null);
    result = visit.cancel(info);
    assertEquals(result, Visit.C_AVAILABLE_TERM);

    info = new VisitInfo(11, 1, 10, "", null);
    result = visit.cancel(info);
    assertEquals(result, Visit.C_EMPTY_CODE);

    info = new VisitInfo(11, 1, 10, "asfasf", null);
    result = visit.cancel(info);
    assertEquals(result, Visit.C_INCORRECT_CODE);

    info = new VisitInfo(11, 1, 10, "511XYM", null);
    result = visit.cancel(info);
    assertEquals(result, Visit.C_SUCCESS);
  }

  @Test
  void book() {
    VisitInfo info = new VisitInfo(-11, 1, 10, "", new MUser("", "", ""));
    String result = visit.book(info);
    assertEquals(result, Visit.NOT_EXIST);

    info = new VisitInfo(11, 1, 11, "", new MUser("", "", ""));
    result = visit.book(info);
    assertEquals(result, Visit.B_BOOKED);

    info = new VisitInfo(11, 1, 13, "", new MUser("", "", ""));
    result = visit.book(info);
    assertEquals(result, Visit.B_LACK_OF_DATA);

    info = new VisitInfo(11, 1, 13, "", new MUser("Piotr", "Kalota", "222-444-5555"));
    result = visit.book(info);
    assertEquals(result, Visit.B_SUCCESS);
  }
}