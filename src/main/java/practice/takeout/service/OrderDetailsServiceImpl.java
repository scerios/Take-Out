package practice.takeout.service;

import org.springframework.stereotype.Service;
import practice.takeout.dto.BurgerQuantityDto;
import practice.takeout.model.Order;
import practice.takeout.model.OrderDetails;
import practice.takeout.repository.OrderDetailsRepository;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
  private OrderDetailsRepository repository;

  public OrderDetailsServiceImpl(OrderDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void addOrderDetails(Order order, List<BurgerQuantityDto> burgerQuantityDtoList) {
    for (int i = 0; i < burgerQuantityDtoList.size(); i++) {
      OrderDetails orderDetails = new OrderDetails();
      orderDetails.setOrder(order);
      orderDetails.setBurgerId(burgerQuantityDtoList.get(i).getBurgerId());
      orderDetails.setQuantity(burgerQuantityDtoList.get(i).getQuantity());
      repository.save(orderDetails);
    }
  }
}
