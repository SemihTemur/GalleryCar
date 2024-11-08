package com.semih.service;

import com.semih.dto.request.CustomerRequest;
import com.semih.dto.response.AccountResponse;
import com.semih.dto.response.AddressResponse;
import com.semih.dto.response.CustomerResponse;
import com.semih.entity.Account;
import com.semih.entity.Address;
import com.semih.entity.Customer;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AddressService addressService;

    private final AccountService accountService;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService, AccountService accountService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
        this.accountService = accountService;
    }

    private Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequest, customer);

        Address address = addressService.getAddressById(customerRequest.getAddressId());
        Account account = accountService.getAccountById(customerRequest.getAccountId());

        customer.setAddress(address);
        customer.setAccount(account);

        return customer;
    }

    private Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));

    }


    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        CustomerResponse customerResponse = new CustomerResponse();
        AddressResponse addressResponse = new AddressResponse();
        AccountResponse accountResponse = new AccountResponse();

        Customer savedCustomer = customerRepository.save(createCustomer(customerRequest));

        BeanUtils.copyProperties(savedCustomer, customerResponse);
        BeanUtils.copyProperties(savedCustomer.getAddress(), addressResponse);
        BeanUtils.copyProperties(savedCustomer.getAccount(), accountResponse);

        customerResponse.setAddress(addressResponse);
        customerResponse.setAccount(accountResponse);

        return customerResponse;

    }

    public CustomerResponse getCustomerResponseById(Long id) {
        CustomerResponse customerResponse = new CustomerResponse();
        Customer savedCustomer = getCustomerById(id);
        BeanUtils.copyProperties(savedCustomer, customerResponse);

        AddressResponse addressResponse = new AddressResponse();
        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(savedCustomer.getAccount(), accountResponse);
        BeanUtils.copyProperties(savedCustomer.getAddress(), addressResponse);

        customerResponse.setAddress(addressResponse);
        customerResponse.setAccount(accountResponse);

        return customerResponse;

    }

}
