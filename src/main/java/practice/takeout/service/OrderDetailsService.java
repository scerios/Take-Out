package practice.takeout.service;

import practice.takeout.model.OrderDetails;

public interface OrderDetailsService {
  void addOrderDetails(OrderDetails orderDetails, long burgerId);
}
