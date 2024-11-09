package com.semih.dto.response;

import com.semih.entity.Car;
import com.semih.entity.Gallerist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleristCarResponse extends BaseResponse {

    private GalleristResponse gallerist;

    private CarResponse car;

}
