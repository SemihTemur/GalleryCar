package com.semih.service;

import com.semih.dto.request.CarRequest;
import com.semih.dto.response.CarResponse;
import com.semih.entity.Car;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private Car createCar(CarRequest carRequest){
        Car car = new Car();
        BeanUtils.copyProperties(carRequest, car);
        return carRepository.save(car);
    }

    public CarResponse saveCar(CarRequest carRequest) {
        CarResponse carResponse = new CarResponse();
        Car car = createCar(carRequest);
        BeanUtils.copyProperties(car, carResponse);
        return carResponse;
    }

    public Car getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return car.get();
        }
        throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
    }

}
