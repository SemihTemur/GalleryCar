package com.semih.controller;

import com.semih.dto.response.CurrencyRatesResponse;
import com.semih.service.CurrencyRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="rest/api/")
public class CurrencyRatesController extends BaseController {

    private final CurrencyRatesService currencyRatesService;

    public CurrencyRatesController(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    @GetMapping(path="currency-rates")
    private RootEntity<CurrencyRatesResponse> getCurrencyRate(@RequestParam String startDate, @RequestParam String endDate) {
       return ok(currencyRatesService.getCurrencyRate(startDate, endDate));
    }

}
