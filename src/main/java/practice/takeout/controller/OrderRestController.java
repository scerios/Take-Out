package practice.takeout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.takeout.dto.OrderStatUpdDto;
import practice.takeout.model.Meal;
import practice.takeout.service.MealService;

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
    } else {
      Meal mealToUpdate = mealService.getMealById(id);
      mealToUpdate.setStatus(orderStatUpdDto.getStatus());
      mealService.saveMeal(mealToUpdate);
      return new ResponseEntity(HttpStatus.ACCEPTED);
    }
  }
}
