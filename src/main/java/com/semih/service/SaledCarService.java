package com.semih.service;

import com.semih.dto.request.SaledCarRequest;
import com.semih.dto.response.*;
import com.semih.entity.Account;
import com.semih.entity.Car;
import com.semih.entity.Customer;
import com.semih.entity.SaledCar;
import com.semih.enums.CarStatusType;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.repository.SaledCarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaledCarService {

    private final SaledCarRepository saledCarRepository;

    private final GalleristService galleristService;

    private final CarService carService;

    private final CustomerService customerService;

    private final CurrencyRatesService currencyRatesService;

    public SaledCarService(SaledCarRepository saledCarRepository, GalleristService galleristService, CarService carService, CustomerService customerService,CurrencyRatesService currencyRatesService) {
        this.saledCarRepository = saledCarRepository;
        this.galleristService = galleristService;
        this.carService = carService;
        this.customerService = customerService;
        this.currencyRatesService = currencyRatesService;
    }

    private boolean areCurrenciesEqual(String accountCurrency, String carCurrency) {
        return accountCurrency.equals(carCurrency);
    }

    private BigDecimal carPrice(BigDecimal carPrice) {
        BigDecimal usd = new BigDecimal(currencyRatesService.getCurrencyRate("08-11-2024","08-11-2024").getItems().get(0).getUsd());
        return usd.multiply(carPrice);
    }

    private BigDecimal remainingCustomerBalance(Customer customer,Car car) {
        Account account = customer.getAccount();
        return account.getAmount().subtract(carPrice(car.getPrice()));
    }

    private boolean canCustomerBuyCar(Customer customer, Car car) {
        Account account = customer.getAccount();
        BigDecimal resultCarPrice = car.getPrice();

        if(!areCurrenciesEqual(account.getCurrencyType().toString(), car.getCurrencyType().toString())) {
           resultCarPrice = carPrice(car.getPrice());
        }

        return account.getAmount().compareTo(resultCarPrice) >= 0;
    }

    private SaledCar createSaledCar(SaledCarRequest saledCarRequest) {
        SaledCar saledCar = new SaledCar();
        saledCar.setGallerist(galleristService.getGalleristById(saledCarRequest.getGalleristId()));
        saledCar.setCar(carService.getCarById(saledCarRequest.getCarId()));
        saledCar.setCustomer(customerService.getCustomerById(saledCarRequest.getCustomerId()));

        if(saledCar.getCar().getCarStatusType().equals(CarStatusType.SALED)){
            throw new BaseException(new ErrorMessage("SALED", MessageType.CAR_STATUS_SOLD));
        }

        if(!canCustomerBuyCar(saledCar.getCustomer(), saledCar.getCar())){
           throw new BaseException(new ErrorMessage("no money",MessageType.INSUFFICIENT_FUNDS));
        }

        saledCar.getCustomer().getAccount().setAmount(remainingCustomerBalance(saledCar.getCustomer(),saledCar.getCar()));
        saledCar.getCar().setCarStatusType(CarStatusType.SALED);
        return saledCarRepository.save(saledCar);
    }

    public SaledCarResponse saveSaledCar(SaledCarRequest saledCarRequest) {
        SaledCarResponse saledCarResponse = new SaledCarResponse();
        GalleristResponse galleristResponse = new GalleristResponse();
        CarResponse carResponse = new CarResponse();
        CustomerResponse customerResponse = new CustomerResponse();
        AddressResponse addressResponseGallerist = new AddressResponse();
        AddressResponse addressResponseCustomer = new AddressResponse();

        SaledCar savedSaledCar = createSaledCar(saledCarRequest);
        BeanUtils.copyProperties(savedSaledCar.getGallerist(), galleristResponse);
        BeanUtils.copyProperties(savedSaledCar.getCar(), carResponse);
        BeanUtils.copyProperties(savedSaledCar.getCustomer(), customerResponse);
        BeanUtils.copyProperties(savedSaledCar.getGallerist().getAddress(), addressResponseGallerist);
        BeanUtils.copyProperties(savedSaledCar.getCustomer().getAddress(), addressResponseCustomer);

        saledCarResponse.setGallerist(galleristResponse);
        saledCarResponse.setCar(carResponse);
        saledCarResponse.setCustomer(customerResponse);
        saledCarResponse.getGallerist().setAddress(addressResponseGallerist);
        saledCarResponse.getCustomer().setAddress(addressResponseCustomer);

        return saledCarResponse;
    }

}
