package com.semih.controller;

import com.semih.dto.request.SaledCarRequest;
import com.semih.dto.response.SaledCarResponse;
import com.semih.service.SaledCarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "rest/api")
public class SaledCarController extends BaseController{

    private final SaledCarService saledCarService;

    public SaledCarController(SaledCarService saledCarService) {
        this.saledCarService = saledCarService;
    }

    @PostMapping(path="/saveSaledCar")
    public RootEntity<SaledCarResponse> saveSaledCar(@RequestBody SaledCarRequest saledCarRequest) {
        return ok(saledCarService.saveSaledCar(saledCarRequest));
    }


}
