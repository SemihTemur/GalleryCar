package com.semih.controller;

import com.semih.dto.request.CarRequest;
import com.semih.dto.response.CarResponse;
import com.semih.service.CarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="rest/api")
public class CarController extends BaseController{

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/saveCar")
    public RootEntity<CarResponse> saveCar(@RequestBody CarRequest carRequest) {
        return ok(carService.saveCar(carRequest));
    }
}
