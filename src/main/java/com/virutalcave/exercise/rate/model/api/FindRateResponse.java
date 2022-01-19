package com.virutalcave.exercise.rate.model.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FindRateResponse {

    private String symbol;

    private String code;

    private String price;

}
