package com.semih.dto.response;

import com.semih.entity.BaseEntity;
import com.semih.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse extends BaseEntity {

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;

}
