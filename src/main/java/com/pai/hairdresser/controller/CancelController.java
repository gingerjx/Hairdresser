package com.pai.hairdresser.controller;

import com.pai.hairdresser.service.CalendarInfo;
import com.pai.hairdresser.service.Visit;
import com.pai.hairdresser.service.VisitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CancelController {
  @Autowired
  private CalendarInfo calendarInfo;
  @Autowired
  private Visit visit;

  @RequestMapping("/cancel")
  public String cancelView(@ModelAttribute VisitInfo visitInfo, Model model) {
    model.addAttribute("months", calendarInfo.getMonths());
    model.addAttribute("bookedVisits", calendarInfo.getRecentVisits(6, true));
    return "cancel";
  }

  @RequestMapping("/cancelation")
  public String cancelVisit(@ModelAttribute VisitInfo info, Model model) {

    String message = visit.cancel(info);

    model.addAttribute("term", "Term " + info.getDay() + "/" + info.getMonth() + "  " + info.getHour() + ":00");

    if (message.equals(Visit.C_SUCCESS)) {
      model.addAttribute("success", true);
    }
    else {
      model.addAttribute("success", false);
    }

    model.addAttribute("code", "");
    model.addAttribute("message", message);

    return "message";
  }
}
