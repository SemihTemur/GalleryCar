package com.semih.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleristCarRequest {

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;

}