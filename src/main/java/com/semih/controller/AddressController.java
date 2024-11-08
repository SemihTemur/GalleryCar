package com.semih.controller;

import com.semih.dto.request.AddressRequest;
import com.semih.dto.response.AddressResponse;
import com.semih.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="rest/api")
public class AddressController extends BaseController{

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path="/saveAddress")
    public RootEntity<AddressResponse>  saveAddress(@Valid @RequestBody AddressRequest addressRequest) {
        return ok(addressService.saveAddress(addressRequest));
    }
}
