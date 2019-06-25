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

  @GetMapping("/")
  public String getIndexPage() {
    return "index";
  }

  @GetMapping("/login")
  public String logIn(Cus cus, ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    final String getPwdByEmailQuery = "SELECT pwd FROM customers WHERE email = " + "\"" + cus.getEmail() + "\"" + ";";
    final String getEmailByEmailQuery = "SELECT email FROM customers WHERE email = " + "\"" + cus.getEmail() + "\"" + ";";
    if (cusService.getDataFromDbByQuery(getPwdByEmailQuery).equals(cus.getPwd())) {
      String pin = cusService.generatePinForReference();
      cusService.setTempCusPin(cus.getId(), pin);
      redirectAttributes.addAttribute("id", cus.getId());
      return "redirect:/homepage/{id}";
    } else {
      if (!cusService.getDataFromDbByQuery(getEmailByEmailQuery).equals(cus.getEmail())) {
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

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String sendRegister(RedirectAttributes redirectAttributes, Cus cus, CusDetails cusDetails, ErrorMsg errorMsg) {
    final String query = "SELECT email FROM customers WHERE email = " + "\"" + cus.getEmail() + "\"" + ";";
    if (cusService.getDataFromDbByQuery(query).equals(cus.getEmail())) {
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
