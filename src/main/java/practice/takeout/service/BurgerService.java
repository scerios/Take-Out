package practice.takeout.service;

import practice.takeout.model.Burger;

public interface BurgerService {
  void addBurger(Burger burger);

  Burger getBurgerById(long id);

  long getBurgerIdByQuery(String bun, String meat, String cheese, String sauce, byte onionMarmalade, byte friedEgg,
                          byte jalapenos, byte pineapple);
}
