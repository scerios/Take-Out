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
  private final String query = "SELECT * FROM customers WHERE email = " + "\"";
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

  @GetMapping("/")
  public String getIndexPage() {
    return "index";
  }

  @GetMapping("/login")
  public String logIn(Cus cus, ErrorMsg errorMsg, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    String[] dataByQuery = cusService.getDataFromDbByQuery(query + cus.getEmail() + "\"");
    long extractedId = Long.parseLong(dataByQuery[0]);
    String extractedEmail = dataByQuery[1];
    String extractedPwd = dataByQuery[2];
    if (extractedPwd.equals(cus.getPwd())) {
      request.getSession().setAttribute("CUS_SESSION_ID", extractedId);
      return "redirect:/homepage";
    } else {
      if (!extractedEmail.equals(cus.getEmail())) {
        errorMsg.setWrongEmail("wrongEmail");
        redirectAttributes.addFlashAttribute("wrongEmail", errorMsg.getWrongEmail());
        return "redirect:/";
      } else {
        errorMsg.setWrongPwd("wrongPwd");
        redirectAttributes.addFlashAttribute("wrongPwd", errorMsg.getWrongPwd());
        return "redirect:/";
      }
    }
  }

  @GetMapping("/homepage")
  public String getHomePage(Model model, HttpSession session) {
    long id = (long) session.getAttribute("CUS_SESSION_ID");
    model.addAttribute("cusDetails", cusDetailsService.getDetailsById(id));
    return "homepage";
  }


  @PostMapping("/endSession")
  public String endSession(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/";
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String sendRegister(RedirectAttributes redirectAttributes, Cus cus, CusDetails cusDetails, ErrorMsg errorMsg) {
    String[] dataByQuery = cusService.getDataFromDbByQuery(query + cus.getEmail() + "\"");
    String email = dataByQuery[1];
    if (cus.getEmail().equals(email)) {
      errorMsg.setAlreadyRegistered("alreadyRegistered");
      redirectAttributes.addFlashAttribute("alreadyRegistered", errorMsg.getAlreadyRegistered());
      return "redirect:/register";
    } else {
      cusService.addCus(cus);
      cusService.addDetailsToCus(cus.getId(), cusDetails);
      cusDetailsService.addDetails(cusDetails);
      return "redirect:/";
    }
  }
}
