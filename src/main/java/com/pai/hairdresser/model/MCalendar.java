package com.pai.hairdresser.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MCalendar {
  @Value("${calendar.url}")
  private String xmlPath;
  private HashMap<Integer, MMonth> months;
  private Document dom;
  private Element root;
  private Transformer transformer;

  @PostConstruct
  public void postConstructor() {
    months = new HashMap<>();
    initXMLDocument();
    initTransformer();
    load();
  }

  private void initXMLDocument() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      dom = builder.parse(xmlPath);
      root = dom.getDocumentElement();
    } catch (ParserConfigurationException | SAXException | IOException e){
      e.printStackTrace();
    }
  }

  private void initTransformer() {
    try {
      root.normalize();
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    } catch (TransformerConfigurationException e){
      e.printStackTrace();
    }
  }

  private void save() {
    try {
      StreamResult result = new StreamResult(new File(xmlPath));
      transformer.transform(new DOMSource(dom), result);
    } catch (TransformerException e){
      e.printStackTrace();
    }
  }

  private void load() {
    NodeList monthElements = root.getElementsByTagName("month");
    for (int i=0; i<monthElements.getLength(); ++i) {
      Element monthElement = (Element) monthElements.item(i);
      Integer id = Integer.parseInt(monthElement.getAttribute("id"));
      months.put(id, MMonth.load(monthElement, id));
    }
  }

  public void update(int month, int day, int hour, boolean booked, String authenticationCode, MUser user) {
    if (!exist(month, day, hour)) {
      return;
    }

    if (authenticationCode == null || user == null) {
      return;
    }

    MMonth mMonth = months.get(month);
    MDay mDay = mMonth.getDay(day);
    MHour mHour = mDay.getHour(hour);

    mHour.setBooked(booked);
    mHour.setAuthenticationCode(authenticationCode);
    mHour.setUser(user);

    save();
  }

  public boolean exist(int month, int day, int hour) {
    MMonth mMonth = months.get(month);
    if (mMonth == null) {
      return false;
    }

    MDay mDay = mMonth.getDay(day);
    if (mDay == null) {
      return false;
    }

    MHour mHour = mDay.getHour(hour);
    return mHour != null;
  }

  public MDay getDate(int month, int day) {
    MMonth mMonth = months.get(month);
    return mMonth != null ? mMonth.getDay(day) : null;
  }

  public MMonth getMonth(int month) {
    return months.get(month);
  }

  public HashMap<Integer, MMonth> getMonths() {
    return months;
  }

  @Override
  public String toString() {
    String result = "MCalendar{xmlPath='" + xmlPath + "'\n";
    for (Map.Entry<Integer, MMonth> entry : months.entrySet()) {
      result += entry.getValue();
    }

    return result;
  }
}
