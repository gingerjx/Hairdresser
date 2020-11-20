package com.pai.hairdresser.model;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public class MMonth {
  private final Integer month;
  private final HashMap<Integer, MDay> days;

  public MMonth(Integer month) {
    this.month = month;
    this.days = new HashMap<>();
  }

  public static MMonth load(Element monthElement, Integer id) {
    MMonth mMonth = new MMonth(id);
    NodeList dayElements = monthElement.getElementsByTagName("day");

    for (int i=0; i<dayElements.getLength(); ++i) {
      Element dayElement = (Element) dayElements.item(i);
      Integer day_id = Integer.parseInt(dayElement.getAttribute("id"));
      mMonth.days.put(day_id, MDay.load(dayElement, day_id));
    }

    return mMonth;
  }

  public MDay getDay(int day) {
    return days.get(day);
  }

  public HashMap<Integer, MDay> getDays() {
    return days;
  }

  public Integer getMonth() {
    return month;
  }

  @Override
  public String toString() {
    String result = " Month(" + month + "){";
    for (Map.Entry<Integer, MDay> entry : days.entrySet()) {
      result += entry.getValue().toString();
    }

    result += "} ";
    return result;
  }
}
