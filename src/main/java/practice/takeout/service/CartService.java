package practice.takeout.service;

import practice.takeout.model.Cart;

public interface CartService {
  void addBurgerToCart(Cart cart, long burgerId, int quantity);
}
