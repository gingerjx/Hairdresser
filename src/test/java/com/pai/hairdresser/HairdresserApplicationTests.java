package com.pai.hairdresser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "calendar.url=src/test/resources/calendar.xml"})
class HairdresserApplicationTests {

  @Test
  void contextLoads() {
    System.out.println("contextLoads");
  }

}
