package practice.takeout.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.dto.BurgerQuantityDto;
import practice.takeout.dto.CartBurgerDto;
import practice.takeout.model.Cart;
import practice.takeout.model.PopUpMsq;
import java.util.List;

public interface CartService {
  void addBurgerToCart(Cart cart, long burgerId, int quantity);

  void setBurgerAdded(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);

  List<CartBurgerDto> findAllByCusId(long id);

  List<BurgerQuantityDto> getBurgerIdsAndQuantitiesFromCartByCusId(long id);

  void deleteAllByCus_Id(long id);

  void setCartIsEmpty(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes);
}
