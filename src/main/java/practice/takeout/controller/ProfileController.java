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

  @GetMapping("/login")
  public String logIn(Cus cus, ErrorMsg errorMsg, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    String[] dataByQuery = cusService.getDataFromDbByQuery(cus.getEmail());
    if (dataByQuery[1] == null) {
      cusService.setWrongEmail(errorMsg, redirectAttributes);
      return "redirect:/";
    }
    long extractedId = Long.parseLong(dataByQuery[0]);
    String extractedPwd = dataByQuery[2];
    if (extractedPwd.equals(cus.getPwd())) {
      if (cusService.getIsLoggedIn(extractedId) == 0) {
        request.getSession().setAttribute("CUS_SESSION_ID", extractedId);
        request.getSession().setMaxInactiveInterval(600);
        cusService.setIsLoggedIn(extractedId, (byte) 1);
        return "redirect:/homepage";
      }
      cusService.setAlreadyLoggedIn(errorMsg, redirectAttributes);
      return "redirect:/";
    }
    cusService.setWrongPwd(errorMsg, redirectAttributes);
    return "redirect:/";
  }

  @PostMapping("/endSession")
  public String endSession(HttpServletRequest request, HttpSession session) {
    cusService.setIsLoggedIn(cusService.getCusSessionId(session), (byte) 0);
    request.getSession().invalidate();
    return "redirect:/";
  }

  @PostMapping("/register")
  public String sendRegister(RedirectAttributes redirectAttributes, Cus cus, CusDetails cusDetails, ErrorMsg errorMsg) {
    String[] dataByQuery = cusService.getDataFromDbByQuery(cus.getEmail());
    String extractedEmail = dataByQuery[1];
    if (cus.getEmail().equals(extractedEmail)) {
      cusService.setAlreadyRegistered(errorMsg, redirectAttributes);
      return "redirect:/register";
    } else {
      cusService.addCus(cus);
      cusService.addDetailsToCus(cus.getId(), cusDetails);
      cusDetailsService.addDetails(cusDetails);
      return "redirect:/";
    }
  }
}
