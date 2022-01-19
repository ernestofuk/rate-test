package com.virutalcave.exercise.rate.mapper;

import com.virutalcave.exercise.rate.model.api.CreateRateRequest;
import com.virutalcave.exercise.rate.model.api.FindRateResponse;
import com.virutalcave.exercise.rate.model.api.UpdateRateRequest;
import com.virutalcave.exercise.rate.model.entity.Rate;
import com.virutalcave.exercise.rate.model.thirdparties.currency.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

@Component
public class RateMapper {

    public Rate createRequestToRate(CreateRateRequest request) {
        Rate rate = new Rate();
        return getRate(rate, request.getBrandId(), request.getProductId(),
                request.getEndDate(), request.getStartDate(), request.getPrice(),
                request.getCurrencyCode());
    }

    public Rate updateRequestToRate(UpdateRateRequest request) {
        Rate rate = new Rate();
        rate.setId(request.getId());
        return getRate(rate, request.getBrandId(), request.getProductId(),
                request.getEndDate(), request.getStartDate(),
                request.getPrice(), request.getCurrencyCode());
    }

    private Rate getRate(Rate rate, Integer brandId, Integer productId,
                         LocalDate endDate, LocalDate startDate, BigDecimal price,
                         String currencyCode) {
        rate.setBrandId(brandId);
        rate.setProductId(productId);
        rate.setEndDate(endDate);
        rate.setStartDate(startDate);
        rate.setPrice(price);
        rate.setCurrencyCode(currencyCode);
        return rate;
    }

    public FindRateResponse rateToResponse(Rate rate, Currency currency) {
        DecimalFormat decimalFormat = new DecimalFormat();
        FindRateResponse response = new FindRateResponse();
        decimalFormat.setMinimumFractionDigits(currency.getDecimals());
        response.setPrice(decimalFormat.format(rate.getPrice()));
        response.setSymbol(currency.getSymbol());
        response.setCode(currency.getCode());
        return response;
    }

}
