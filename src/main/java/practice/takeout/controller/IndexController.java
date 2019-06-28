package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Cus;
import practice.takeout.model.CusDetails;
import practice.takeout.model.ErrorMsg;
import practice.takeout.service.CusDetailsServiceImpl;
import practice.takeout.service.CusServiceImpl;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
  private CusServiceImpl cusService;
  private CusDetailsServiceImpl cusDetailsService;

  @Autowired
  public IndexController(CusServiceImpl cusService, CusDetailsServiceImpl cusDetailsService) {
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

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @GetMapping("/addNewAddress")
  public String getAddNewAddressPage(HttpSession session, ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      return "addNewAddress";
    } else {
      cusService.accessDenied(errorMsg, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/homepage")
  public String getHomePage(Model model, HttpSession session, ErrorMsg errorMsg, RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      model.addAttribute("cus", cusService.getCusById(cusService.getCusSessionId(session)));
      return "homepage";
    } else {
      cusService.accessDenied(errorMsg, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/profile")
  public String getProfilePage(Model model, HttpSession session, ErrorMsg errorMsg,
                               RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      model.addAttribute("details", cusDetailsService.findAllByCus_Id(cusService.getCusSessionId(session)));
      return "profile";
    } else {
      cusService.accessDenied(errorMsg, redirectAttributes);
      return "redirect:/";
    }
  }
}
