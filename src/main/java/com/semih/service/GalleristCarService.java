package com.semih.service;

import com.semih.dto.request.GalleristCarRequest;
import com.semih.dto.response.AddressResponse;
import com.semih.dto.response.CarResponse;
import com.semih.dto.response.GalleristCarResponse;
import com.semih.dto.response.GalleristResponse;
import com.semih.entity.Address;
import com.semih.entity.Car;
import com.semih.entity.Gallerist;
import com.semih.entity.GalleristCar;
import com.semih.repository.GalleristCarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GalleristCarService {

    private final GalleristCarRepository galleristCarRepository;

    private final CarService carService;

    private final GalleristService galleristService;

    public GalleristCarService(GalleristCarRepository galleristCarRepository, CarService carService, GalleristService galleristService) {
        this.galleristCarRepository = galleristCarRepository;
        this.carService = carService;
        this.galleristService = galleristService;
    }

    private GalleristCar createGalleristCar(GalleristCarRequest galleristCarRequest){
        GalleristCar galleristCar = new GalleristCar();
        Gallerist gallerist = galleristService.getGalleristById(galleristCarRequest.getGalleristId());
        Car car = carService.getCarById(galleristCarRequest.getCarId());

        galleristCar.setGallerist(gallerist);
        galleristCar.setCar(car);

        return galleristCarRepository.save(galleristCar);

    }

    public GalleristCarResponse saveGalleristCar(GalleristCarRequest galleristCarRequest) {
        GalleristCarResponse galleristCarResponse = new GalleristCarResponse();
        GalleristResponse galleristResponse = new GalleristResponse();
        AddressResponse addressResponse = new AddressResponse();
        CarResponse carResponse = new CarResponse();

        GalleristCar savedGallerist = createGalleristCar(galleristCarRequest);

        BeanUtils.copyProperties(savedGallerist,galleristCarResponse);
        BeanUtils.copyProperties(savedGallerist.getGallerist(),galleristResponse);
        BeanUtils.copyProperties(savedGallerist.getGallerist().getAddress(),addressResponse);
        BeanUtils.copyProperties(savedGallerist.getCar(),carResponse);

        galleristResponse.setAddress(addressResponse);
        galleristCarResponse.setGallerist(galleristResponse);
        galleristCarResponse.setCar(carResponse);

        return galleristCarResponse;

    }
}
