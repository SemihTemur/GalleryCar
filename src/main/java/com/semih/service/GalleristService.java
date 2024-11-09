package com.semih.service;

import com.semih.dto.request.GalleristRequest;
import com.semih.dto.response.AddressResponse;
import com.semih.dto.response.GalleristResponse;
import com.semih.entity.Address;
import com.semih.entity.Gallerist;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import com.semih.repository.GalleristRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GalleristService {

    private final GalleristRepository galleristRepository;

    public GalleristService(GalleristRepository galleristRepository) {
        this.galleristRepository = galleristRepository;
    }

    private Gallerist createGallerist(GalleristRequest galleristRequest) {
        Gallerist gallerist = new Gallerist();
        Address address = new Address();
        BeanUtils.copyProperties(galleristRequest, gallerist);
        BeanUtils.copyProperties(galleristRequest.getAddress(), address);
        gallerist.setAddress(address);
        return galleristRepository.save(gallerist);
    }

    public GalleristResponse saveGallerist(GalleristRequest galleristRequest) {
        GalleristResponse galleristResponse = new GalleristResponse();
        AddressResponse addressResponse = new AddressResponse();

        Gallerist savedGallerist = createGallerist(galleristRequest);
        BeanUtils.copyProperties(savedGallerist, galleristResponse);
        BeanUtils.copyProperties(savedGallerist.getAddress(), addressResponse);

        galleristResponse.setAddress(addressResponse);

        return galleristResponse;

    }

    public Gallerist getGalleristById(Long id) {
        Optional<Gallerist> gallerist = galleristRepository.findById(id);
        if (gallerist.isPresent()) {
            return gallerist.get();
        }
        throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
    }

}
