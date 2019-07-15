package practice.takeout.service;

import practice.takeout.model.OrderDetails;

public interface OrderService {
  void addOrder();

  void addDetailsToOrder(long id, OrderDetails orderDetails);
}
