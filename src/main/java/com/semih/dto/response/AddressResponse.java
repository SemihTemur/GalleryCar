package com.semih.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse extends BaseResponse {

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
