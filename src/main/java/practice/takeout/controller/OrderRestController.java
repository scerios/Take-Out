package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.takeout.dto.MealDetailsDto;
import practice.takeout.dto.OrderStatUpdDto;
import practice.takeout.model.Meal;
import practice.takeout.service.MealService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderRestController {
  private MealService mealService;

  @Autowired
  public OrderRestController(MealService mealService) {
    this.mealService = mealService;
  }

  @PatchMapping("/api/order/{id}")
  public ResponseEntity setOrderStatus(@PathVariable long id, @RequestBody OrderStatUpdDto orderStatUpdDto) {
    if (mealService.getMealById(id) == null) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    if (isStatUpdQueryValid(orderStatUpdDto.getStatus())) {
      Meal mealToUpdate = mealService.getMealById(id);
      mealToUpdate.setStatus(orderStatUpdDto.getStatus());
      mealService.saveMeal(mealToUpdate);
      return new ResponseEntity(HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  private boolean isStatUpdQueryValid(String status) {
    return status.equals("ordered") || status.equals("in progress") || status.equals("done");
  }

  @GetMapping("/api/orders")
  public List<MealDetailsDto> getOrdersByQuery(@RequestParam String type, @RequestParam String status) {
    List<MealDetailsDto> sortedList = new ArrayList<>();
    switch (type) {
      case "all":
        mealService.getAllMealsByStatus(status).forEach(meal -> sortedList.add(mealService.MealToDto(meal)));
        return sortedList;

      case "vegetarian":
        mealService.getAllMealsByTypeAndStatus(type, status).forEach(meal -> sortedList.add(mealService.MealToDto(meal)));
        return sortedList;
    }
    return sortedList;
  }
}
