package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.Order;
import practice.takeout.model.OrderDetails;
import practice.takeout.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
  private OrderDetailsRepository repository;

  public OrderDetailsServiceImpl(OrderDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addOrderDetails(Order order, long burgerId, int quantity) {
    OrderDetails orderDetails = new OrderDetails();
    orderDetails.setOrder(order);
    orderDetails.setBurgerId(burgerId);
    orderDetails.setQuantity(quantity);
    repository.save(orderDetails);
  }
}
