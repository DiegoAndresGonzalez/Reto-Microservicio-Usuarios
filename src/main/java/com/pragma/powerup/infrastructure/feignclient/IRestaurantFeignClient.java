package com.pragma.powerup.infrastructure.feignclient;

import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "restaurantServiceClient", url = "${microservice.restaurantServiceUrl}")
public interface IRestaurantFeignClient {

    @GetMapping("owner/restaurant/{name}")
    RestaurantModel fetchRestaurant(@RequestHeader("Authorization") String authorization, @RequestParam("name") String name);

    @PostMapping("owner/employee/restaurant")
    void fetchEmployeeRestaurant(@RequestHeader("Authorization") String authorization, @RequestParam("employeeId") Long employeeId, @RequestParam("restaurantId") Long restaurantId);

}