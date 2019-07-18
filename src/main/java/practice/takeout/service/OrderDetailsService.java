package practice.takeout.service;

import practice.takeout.model.Order;

public interface OrderDetailsService {
  void addOrderDetails(Order order, long burgerId, int quantity);
}
