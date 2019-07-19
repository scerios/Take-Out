package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.*;
import practice.takeout.service.*;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
  private OrderServiceImpl orderService;
  private OrderDetailsServiceImpl orderDetailsService;
  private CusServiceImpl cusService;
  private CusDetailsServiceImpl cusDetailsService;
  private BurgerServiceImpl burgerService;
  private CartServiceImpl cartService;

  @Autowired
  public OrderController(OrderServiceImpl orderService, OrderDetailsServiceImpl orderDetailsService,
                         CusServiceImpl cusService, CusDetailsServiceImpl cusDetailsService,
                         BurgerServiceImpl burgerService, CartServiceImpl cartService) {
    this.orderService = orderService;
    this.orderDetailsService = orderDetailsService;
    this.cusService = cusService;
    this.cusDetailsService = cusDetailsService;
    this.burgerService = burgerService;
    this.cartService = cartService;
  }

  @PostMapping("/order")
  public String addBurgerToCart(Burger burger, Cart cart, HttpSession session, PopUpMsq popUpMsq,
                                RedirectAttributes redirectAttributes) {
    long burgerId = burgerService.getBurgerIdByQuery(burger.getBun(), burger.getMeat(), burger.getCheese(), burger.getSauce());
    if (burgerId == 0) {
      burgerService.addBurger(burger);
      burgerId = burgerService.getBurgerIdByQuery(burger.getBun(), burger.getMeat(), burger.getCheese(), burger.getSauce());
    }
    cusService.addCartToCus(cusService.getCusSessionId(session), cart);
    cartService.addBurgerToCart(cart, burgerId, cart.getQuantity());
    cartService.setBurgerAdded(popUpMsq, redirectAttributes);
    return "redirect:/order";
  }

  @PostMapping("/cart")
  public String sendOrder(CusDetails cusDetails, PopUpMsq popUpMsq, RedirectAttributes redirectAttributes,
                          HttpSession session) {
    cusDetailsService.addOrderToDetails(cusDetails.getId(),
        orderService.addOrder(cusDetailsService.getDetailsById(cusDetails.getId())));
    orderDetailsService.addOrderDetails(orderService.getLastOrderByCusDetailsId(cusDetails.getId()),
        cartService.getBurgerIdsAndQuantitiesFromCartByCusId(cusService.getCusSessionId(session)));
    cartService.deleteAllByCus_Id(cusService.getCusSessionId(session));
    cusService.setOrderSent(popUpMsq, redirectAttributes);
    return "redirect:/homepage";
  }
}
