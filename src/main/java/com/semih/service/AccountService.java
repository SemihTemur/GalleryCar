package com.semih.service;

import com.semih.dto.request.AccountRequest;
import com.semih.dto.response.AccountResponse;
import com.semih.entity.Account;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private Account createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        BeanUtils.copyProperties(accountRequest, account);
        return account;
    }

    public AccountResponse saveAccount(AccountRequest accountRequest) {
        AccountResponse accountResponse = new AccountResponse();
        Account account = accountRepository.save(createAccount(accountRequest));
        BeanUtils.copyProperties(account, accountResponse);
        return accountResponse;
    }

    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }
        return account.get();
    }



}
