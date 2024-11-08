package com.semih.controller;

import com.semih.dto.request.CustomerRequest;
import com.semih.dto.response.CustomerResponse;
import com.semih.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path="rest/api")
public class CustomerController extends BaseController{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path="/saveCustomer")
    public RootEntity<CustomerResponse> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
       return ok(customerService.saveCustomer(customerRequest));
    }

    @GetMapping("/getCustomer/{id}")
    public RootEntity<CustomerResponse> saveCustomer(@PathVariable Long id) {
        return ok(customerService.getCustomerResponseById(id));
    }

}
