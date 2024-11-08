package com.semih.dto.request;

import com.semih.enums.CurrencyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotBlank
    @Size(min = 3, message = "Account number must be at least 3 characters long")
    private String accountNo;

    @NotBlank
    private String iban;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private CurrencyType currencyType;
}
