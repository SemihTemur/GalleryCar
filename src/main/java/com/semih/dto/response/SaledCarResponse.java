package com.semih.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaledCarResponse extends BaseResponse {

    private GalleristResponse gallerist;

    private CarResponse car;

    private CustomerResponse customer;

}
