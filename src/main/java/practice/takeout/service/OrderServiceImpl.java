package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.CusDetails;
import practice.takeout.model.Order;
import practice.takeout.model.OrderDetails;
import practice.takeout.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
  private OrderRepository repository;

  public OrderServiceImpl(OrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public Order addOrder(CusDetails cusDetails) {
    Order order = new Order();
    order.setCusDetails(cusDetails);
    order.setStatus("ordered");
    repository.save(order);
    return order;
  }

  @Override
  public void addDetailsToOrder(long id, OrderDetails orderDetails) {
    repository.findById(id).get().addOrderDetails(orderDetails);
  }
}
