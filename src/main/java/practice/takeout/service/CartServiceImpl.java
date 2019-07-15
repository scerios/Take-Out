package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.Cart;
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
}
