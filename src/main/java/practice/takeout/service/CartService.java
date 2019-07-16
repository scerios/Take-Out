package practice.takeout.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Cart;
import practice.takeout.model.PopUpMsq;

public interface CartService {
  void addBurgerToCart(Cart cart, long burgerId, int quantity);

  void setBurgerAdded(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);

  Iterable<Cart> findAllByCus_Id(long id);
}
