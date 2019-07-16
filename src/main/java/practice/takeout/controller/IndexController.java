package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.dto.CartBurgerDto;
import practice.takeout.model.*;
import practice.takeout.service.BurgerServiceImpl;
import practice.takeout.service.CartServiceImpl;
import practice.takeout.service.CusDetailsServiceImpl;
import practice.takeout.service.CusServiceImpl;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
  private CusServiceImpl cusService;
  private CusDetailsServiceImpl cusDetailsService;
  private CartServiceImpl cartService;
  private BurgerServiceImpl burgerService;

  @Autowired
  public IndexController(CusServiceImpl cusService, CusDetailsServiceImpl cusDetailsService, CartServiceImpl cartService,
                         BurgerServiceImpl burgerService) {
    this.cusService = cusService;
    this.cusDetailsService = cusDetailsService;
    this.cartService = cartService;
    this.burgerService = burgerService;
  }

  @ModelAttribute
  public void getPage(Model model) {
    model.addAttribute("cus", new Cus());
    model.addAttribute("cusDetails", new CusDetails());
    model.addAttribute("orderDetails", new OrderDetails());
    model.addAttribute("burger", new Burger());
    model.addAttribute("cart", new Cart());
    model.addAttribute("cartBurgerDto", new CartBurgerDto());
  }

  @GetMapping("/")
  public String getIndexPage() {
    return "index";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model, HttpSession session) {
    if (session.getMaxInactiveInterval() == 1800 || session.getMaxInactiveInterval() == 600) {
      return "register";
    } else {
      model.addAttribute("cus", cusService.giveTempCusValues(session));
      model.addAttribute("cusDetails", cusDetailsService.giveTempCusDetailsValues(session));
      session.invalidate();
      return "register";
    }
  }

  @GetMapping("/addNewAddress")
  public String getAddNewAddressPage(HttpSession session, PopUpMsq popUpMsq, RedirectAttributes redirectAttributes,
                                     Model model) {
    if (cusService.isCusHasAccess(session)) {
      if (session.getMaxInactiveInterval() == 599) {
        model.addAttribute(cusDetailsService.giveTempCusDetailsValues(session));
        session.setMaxInactiveInterval(600);
        return "addNewAddress";
      }
      return "addNewAddress";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/homepage")
  public String getHomePage(Model model, HttpSession session, PopUpMsq popUpMsq,
                            RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      model.addAttribute("cus", cusService.getCusById(cusService.getCusSessionId(session)));
      return "homepage";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/profile")
  public String getProfilePage(Model model, HttpSession session, PopUpMsq popUpMsq,
                               RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      model.addAttribute("cus", cusService.getCusById(cusService.getCusSessionId(session)));
      model.addAttribute("details", cusDetailsService.findAllByCus_Id(cusService.getCusSessionId(session)));
      return "profile";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/confirmDel/{id}")
  public String getConfirmDelPage(@PathVariable long id, Model model, HttpSession session, PopUpMsq popUpMsq,
                                  RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session) &&
        cusDetailsService.isCusHasAccessToDetails((long) session.getAttribute("CUS_SESSION_ID"), id)) {
      model.addAttribute("cusDetails", cusDetailsService.getDetailsById(id));
      return "confirmDel";
    } else if (cusService.isCusHasAccess(session)) {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/profile";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/editContact")
  public String getEditContactPage(Model model, HttpSession session, PopUpMsq popUpMsq,
                                   RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      model.addAttribute("cus", cusService.getCusById((long) session.getAttribute("CUS_SESSION_ID")));
      return "editContact";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/order")
  public String getOrderPage(HttpSession session, PopUpMsq popUpMsq, RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      return "order";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }

  @GetMapping("/cart")
  public String getCartPage(Model model, HttpSession session, PopUpMsq popUpMsq, RedirectAttributes redirectAttributes) {
    if (cusService.isCusHasAccess(session)) {
      model.addAttribute("dto", cartService.findAllByCus_Id(cusService.getCusSessionId(session)));
      return "cart";
    } else {
      cusService.accessDenied(popUpMsq, redirectAttributes);
      return "redirect:/";
    }
  }
}
