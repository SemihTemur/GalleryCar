package com.semih.controller;

import com.semih.dto.request.AccountRequest;
import com.semih.dto.response.AccountResponse;
import com.semih.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="rest/api")
public class AccountController extends BaseController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path="/saveAccount")
    public RootEntity<AccountResponse> saveAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return ok(accountService.saveAccount(accountRequest));
    }

}
