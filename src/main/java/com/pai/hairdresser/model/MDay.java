package com.pai.hairdresser.model;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public class MDay {
  private final Integer day;
  private final HashMap<Integer, MHour> hours;

  public MDay(Integer day) {
    this.day = day;
    this.hours = new HashMap<>();
  }

  public static MDay load(Element dayElement, Integer id) {
    MDay mDay = new MDay(id);
    NodeList hourElements = dayElement.getElementsByTagName("hour");

    for (int i=0; i<hourElements.getLength(); ++i) {
      Element hourElement = (Element) hourElements.item(i);
      Integer hour_id = Integer.parseInt(hourElement.getAttribute("id"));
      mDay.hours.put(hour_id, MHour.load(hourElement, hour_id));
    }

    return mDay;
  }

  public MHour getHour(int hour) {
    return hours.get(hour);
  }

  public Integer getDay() {
    return day;
  }

  public HashMap<Integer, MHour> getHours() {
    return hours;
  }

  @Override
  public String toString() {
    String result = " Day(" + day + "){ ";
    for (Map.Entry<Integer, MHour> entry : hours.entrySet()) {
      result += entry.getValue().toString();
    }

    result += " }\n";
    return result;
  }
}
