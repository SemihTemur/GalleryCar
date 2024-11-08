package com.semih.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse extends BaseResponse {

    private String firstName;

    private String lastName;

    private String tckn;

    private Date dateOfBirth;

    private AddressResponse address;

    private AccountResponse account;


}
