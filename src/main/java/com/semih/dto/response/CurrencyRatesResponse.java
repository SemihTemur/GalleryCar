package com.semih.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRatesResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;


}
