package com.semih.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String street;

}
