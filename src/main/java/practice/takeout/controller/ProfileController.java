package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Cus;
import practice.takeout.model.ErrorMsg;
import practice.takeout.service.CusDetailsServiceImpl;
import practice.takeout.service.CusServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
  private CusServiceImpl cusService;
  private CusDetailsServiceImpl cusDetailsService;

  @Autowired
  public ProfileController(CusServiceImpl cusService, CusDetailsServiceImpl cusDetailsService) {
    this.cusService = cusService;
    this.cusDetailsService = cusDetailsService;
  }

  @ModelAttribute
  public void getPage(Model model) {
    model.addAttribute("cus", new Cus());
    model.addAttribute("cusDetails", new CusDetails());
  }

  @PostMapping("/register")
  public String register(Cus cus, CusDetails cusDetails, ErrorMsg errorMsg, HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {
    String[] dataByQuery = cusService.getDataFromDbByQuery(cus.getEmail());
    if (cus.getEmail().equals(dataByQuery[1])) {
      cusService.setAlreadyRegistered(errorMsg, redirectAttributes);
      cusService.giveCusTempSessionToRegister(cus, cusDetails, request);
      return "redirect:/register";
    } else {
      cusService.addCus(cus);
      cusService.addDetailsToCus(cus.getId(), cusDetails);
      cusDetailsService.addDetails(cusDetails);
      return "redirect:/";
    }
  }

  @GetMapping("/login")
  public String logIn(Cus cus, ErrorMsg errorMsg, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    String[] dataByQuery = cusService.getDataFromDbByQuery(cus.getEmail());
    if (dataByQuery[1] == null) {
      cusService.setWrongEmail(errorMsg, redirectAttributes);
      return "redirect:/";
    } else if (dataByQuery[2].equals(cus.getPwd())) {
      cusService.giveCusSessionById(Long.parseLong(dataByQuery[0]), request);
      return "redirect:/homepage";
    } else {
      cusService.setWrongPwd(errorMsg, redirectAttributes);
      return "redirect:/";
    }
  }

  @PostMapping("/endSession")
  public String endSession(HttpServletRequest request) {
    cusService.endCusSession(request);
    return "redirect:/";
  }

  @PostMapping("/addNewAddress")
  public String addNewAddress(CusDetails cusDetails, HttpSession session) {
    cusService.addDetailsToCus(cusService.getCusSessionId(session), cusDetails);
    cusDetailsService.addDetails(cusDetails);
    return "redirect:/profile";
  }

  @DeleteMapping("/confirmDel")
  public String confirmDel(@RequestParam long id) {
    cusDetailsService.delDetailsById(id);
    return "redirect:/profile";
  }

  @PutMapping("/editContact")
  public String editContact(HttpSession session, Cus cus) {
    cusService.updCusById((long) session.getAttribute("CUS_SESSION_ID"), cus);
    return "redirect:/profile";
  }
}
