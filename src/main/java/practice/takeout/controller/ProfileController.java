package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String sendRegister(RedirectAttributes redirectAttributes, Cus cus, CusDetails cusDetails, ErrorMsg errorMsg) {
    if (cusService.isEmailAlreadyRegistered(cus.getEmail())) {
      errorMsg.setErrorMsg("registered");
      redirectAttributes.addFlashAttribute("errorMsg", errorMsg.getErrorMsg());
      return "redirect:/register";
    } else {
      cusService.addCus(cus);
      cusService.addDetailsToCus(cus.getId(), cusDetails);
      cusDetailsService.addDetails(cusDetails);
      return "redirect:/";
    }
  }
}
