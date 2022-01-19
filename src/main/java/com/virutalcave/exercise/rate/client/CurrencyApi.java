package com.virutalcave.exercise.rate.client;

import com.virutalcave.exercise.rate.model.thirdparties.currency.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "currencies", url = "${currency.server.url}")
public interface CurrencyApi {

    @GetMapping("/v1/currencies")
    List<Currency> findCurrencies();

    @GetMapping("/v1/currencies/{currencyCode}")
    Currency findByCode(@PathVariable String currencyCode);

}
