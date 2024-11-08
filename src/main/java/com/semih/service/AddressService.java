package com.semih.service;

import com.semih.dto.request.AddressRequest;
import com.semih.dto.response.AddressResponse;
import com.semih.entity.Address;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.repository.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private Address createAddress(AddressRequest addressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequest, address);
        return address;
    }

    public AddressResponse saveAddress(AddressRequest addressRequest) {
        AddressResponse addressResponse = new AddressResponse();
        Address savedAddress = addressRepository.save(createAddress(addressRequest));
        BeanUtils.copyProperties(savedAddress, addressResponse);
        return addressResponse;
    }

    public Address getAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }
        return address.get();
    }

}
