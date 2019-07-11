package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.Order;
import practice.takeout.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
  private OrderRepository repository;

  public OrderServiceImpl(OrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addOrder(long cusId) {
    Order order = new Order();
    order.setCusId(cusId);
    order.setStatus("ordered");
    repository.save(order);
  }
}
