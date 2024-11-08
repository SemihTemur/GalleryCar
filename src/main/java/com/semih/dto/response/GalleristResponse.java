package com.semih.dto.response;

import com.semih.dto.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleristResponse extends BaseResponse {

    private String firstName;

    private String lastName;

    private AddressResponse address;

}
