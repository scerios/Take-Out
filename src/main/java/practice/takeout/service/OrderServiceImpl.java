package practice.takeout.service;

import org.springframework.stereotype.Service;
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
  public void addOrder() {
    Order order = new Order();
    order.setStatus("ordered");
    repository.save(order);
  }

  @Override
  public void addDetailsToOrder(long id, OrderDetails orderDetails) {
    repository.findById(id).get().addOrderDetails(orderDetails);
  }
}
