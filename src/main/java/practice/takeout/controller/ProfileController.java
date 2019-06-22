package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import practice.takeout.model.Address;
import practice.takeout.model.Cus;
import practice.takeout.service.AddressServiceImpl;
import practice.takeout.service.CusServiceImpl;

@Controller
public class ProfileController {
  private CusServiceImpl cusService;
  private AddressServiceImpl addressService;

  @Autowired
  public ProfileController(CusServiceImpl cusService, AddressServiceImpl addressService) {
    this.cusService = cusService;
    this.addressService = addressService;
  }

  @ModelAttribute
  public void getPage(Model model) {
    model.addAttribute("cus", new Cus());
    model.addAttribute("address", new Address());
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String sendRegister(Cus cus, Address address) {
    cusService.addCus(cus);
    cusService.addAddressToCus(cus.getId(), address);
    addressService.addAddress(address);
    return "redirect:/";
  }
}
