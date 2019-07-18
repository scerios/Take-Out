package practice.takeout.service;

import practice.takeout.dto.BurgerQuantityDto;
import practice.takeout.model.Order;

import java.util.List;

public interface OrderDetailsService {
  void addOrderDetails(Order order, List<BurgerQuantityDto> burgerQuantityDtoList);
}
