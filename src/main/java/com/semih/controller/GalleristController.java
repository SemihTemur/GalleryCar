package com.semih.controller;


import com.semih.dto.request.GalleristRequest;
import com.semih.dto.response.GalleristResponse;
import com.semih.service.GalleristService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rest/api")
public class GalleristController extends BaseController {

    private final GalleristService galleristService;

    public GalleristController(GalleristService galleristService) {
        this.galleristService = galleristService;
    }

    @PostMapping(path="/saveGallerist")
    public RootEntity<GalleristResponse> saveGallerist(@RequestBody GalleristRequest galleristRequest) {
       return ok(galleristService.saveGallerist(galleristRequest));
    }
}
