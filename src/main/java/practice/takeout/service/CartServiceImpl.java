package practice.takeout.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.takeout.model.Cart;
import practice.takeout.model.PopUpMsq;
import practice.takeout.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
  private CartRepository repository;

  public CartServiceImpl(CartRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addBurgerToCart(Cart cart, long burgerId, int quantity) {
    cart.setBurgerId(burgerId);
    cart.setQuantity(quantity);
    repository.save(cart);
  }

  @Override
  public void setBurgerAdded(PopUpMsq popUpMsq, RedirectAttributes redirectAttributes) {
    popUpMsq.setAddedToCart("addedToCart");
    redirectAttributes.addFlashAttribute("addedToCart", popUpMsq.getAddedToCart());
  }

  @Override
  public Iterable<Cart> findAllByCus_Id(long id) {
    return repository.findAllByCus_Id(id);
  }
}
