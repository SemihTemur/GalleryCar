package com.semih.controller;

import com.semih.dto.request.GalleristCarRequest;
import com.semih.dto.response.GalleristCarResponse;
import com.semih.service.GalleristCarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="rest/api")
public class GalleristCarController extends BaseController {

    private  final GalleristCarService galleristCarService;

    public GalleristCarController(GalleristCarService galleristCarService) {
        this.galleristCarService = galleristCarService;
    }

    @PostMapping(path="/save")
    public RootEntity<GalleristCarResponse> addGalleristCar(@RequestBody GalleristCarRequest galleristCarRequest) {
        return ok(galleristCarService.saveGalleristCar(galleristCarRequest));
    }

}
