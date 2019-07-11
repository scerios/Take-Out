package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.model.OrderDetails;
import practice.takeout.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
  private OrderDetailsRepository repository;

  public OrderDetailsServiceImpl(OrderDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addOrderDetails(OrderDetails orderDetails, long burgerId) {
    orderDetails.setBurgerId(burgerId);
    repository.save(orderDetails);
  }
}
