package com.semih.dto.response;

import com.semih.enums.CarStatusType;
import com.semih.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse extends BaseResponse {

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;

}
