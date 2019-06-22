package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.takeout.dto.OrderDetailsDto;
import practice.takeout.dto.OrderStatUpdDto;
import practice.takeout.model.Meal;
import practice.takeout.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderRestController {
  private OrderService orderService;

  @Autowired
  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PatchMapping("/api/order/{id}")
  public ResponseEntity setOrderStatus(@PathVariable long id, @RequestBody OrderStatUpdDto orderStatUpdDto) {
    if (orderService.getMealById(id) == null) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    if (isStatUpdQueryValid(orderStatUpdDto.getStatus())) {
      Meal mealToUpdate = orderService.getMealById(id);
      mealToUpdate.setStatus(orderStatUpdDto.getStatus());
      orderService.saveMeal(mealToUpdate);
      return new ResponseEntity(HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  private boolean isStatUpdQueryValid(String status) {
    return status.equals("ordered") || status.equals("in progress") || status.equals("done");
  }

  @GetMapping("/api/orders")
  public List<OrderDetailsDto> getOrdersByQuery(@RequestParam String type, @RequestParam String status) {
    List<OrderDetailsDto> sortedList = new ArrayList<>();
    switch (type) {
      case "all":
        orderService.getAllMealsByStatus(status).forEach(meal -> sortedList.add(orderService.MealToDto(meal)));
        return sortedList;

      case "vegetarian":
        orderService.getAllMealsByTypeAndStatus(type, status).forEach(meal -> sortedList.add(orderService.MealToDto(meal)));
        return sortedList;
    }
    return sortedList;
  }
}
