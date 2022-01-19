package com.virutalcave.exercise.rate.model.api;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateRateRequest {

    private Integer id;

    private Integer brandId;

    private Integer productId;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal price;

    private String currencyCode;
}
