package practice.takeout.service;

import practice.takeout.model.CusDetails;
import practice.takeout.model.Order;
import practice.takeout.model.OrderDetails;

public interface OrderService {
  Order addOrder(CusDetails cusDetails);

  void addDetailsToOrder(long id, OrderDetails orderDetails);

  Order getLastOrderByCusDetailsId(long id);
}
