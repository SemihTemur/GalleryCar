package com.semih.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRatesItems {

    @JsonProperty("Tarih")
    private String date;

    @JsonProperty("TP_DK_USD_A")
    private String usd;

}
