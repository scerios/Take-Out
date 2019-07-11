package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import practice.takeout.model.Burger;
import practice.takeout.model.OrderDetails;
import practice.takeout.service.BurgerServiceImpl;
import practice.takeout.service.CusServiceImpl;
import practice.takeout.service.OrderDetailsServiceImpl;
import practice.takeout.service.OrderServiceImpl;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
  private OrderServiceImpl orderService;
  private OrderDetailsServiceImpl orderDetailsService;
  private CusServiceImpl cusService;
  private BurgerServiceImpl burgerService;

  @Autowired
  public OrderController(OrderServiceImpl orderService, OrderDetailsServiceImpl orderDetailsService,
                         CusServiceImpl cusService, BurgerServiceImpl burgerService) {
    this.orderService = orderService;
    this.orderDetailsService = orderDetailsService;
    this.cusService = cusService;
    this.burgerService = burgerService;
  }

  @PostMapping("/order")
  public String addBurgerToCart(Burger burger, OrderDetails orderDetails, HttpSession session) {
    long burgerId = burgerService.getBurgerIdByQuery(burger.getBun(), burger.getMeat(), burger.getCheese(), burger.getSauce());
    if (burgerId == 0) {
      burgerService.addBurger(burger);
      burgerId = burgerService.getBurgerIdByQuery(burger.getBun(), burger.getMeat(), burger.getCheese(), burger.getSauce());
    }
    orderDetailsService.addOrderDetails(orderDetails, burgerId);
    orderService.addOrder(cusService.getCusSessionId(session));
    return "redirect:/order";
  }
}
