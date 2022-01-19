package com.virutalcave.exercise.rate.service;

import com.virutalcave.exercise.rate.client.CurrencyApi;
import com.virutalcave.exercise.rate.mapper.RateMapper;
import com.virutalcave.exercise.rate.model.api.CreateRateRequest;
import com.virutalcave.exercise.rate.model.api.UpdateRateRequest;
import com.virutalcave.exercise.rate.model.entity.Rate;
import com.virutalcave.exercise.rate.model.thirdparties.currency.Currency;
import com.virutalcave.exercise.rate.repository.RateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceImplTest {

    @InjectMocks
    private RateServiceImpl service;

    @Mock
    private CurrencyApi currencyApi;

    @Mock
    private RateRepository rateRepository;

    @Spy
    private RateMapper rateMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createRate() {
        Mono<Rate> rateMono = Mono.just(getRate());
        when(rateRepository.save(any())).thenReturn(rateMono);

        service.createRate(new CreateRateRequest());

        verify(rateRepository, times(1)).save(any());
        verify(rateMapper, times(1)).createRequestToRate(any());
    }

    @Test
    public void updateRate() {
        Mono<Rate> rateMono = Mono.just(getRate());
        when(rateRepository.save(any())).thenReturn(rateMono);

        service.updateRate(new UpdateRateRequest());

        verify(rateRepository, times(1)).save(any());
        verify(rateMapper, times(1)).updateRequestToRate(any());
    }

    @Test
    public void findById() {
        Mono<Rate> rateMono = Mono.just(getRate());
        when(rateRepository.findById(anyInt())).thenReturn(rateMono);

        service.findById(1);

        verify(rateRepository, times(1)).findById(anyInt());
    }

    @Test
    public void removeRate() {
        Mono<Void> mono = Mono.just("test").then();
        when(rateRepository.deleteById(anyInt())).thenReturn(mono);

        service.removeRate(1);

        verify(rateRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void findByDate() {
        Flux<Rate> rateMono = Flux.just(getRate());

        when(rateRepository.findByStartDateOrEndDate(any(), any())).thenReturn(rateMono);

        List<Currency> currencies = new ArrayList<>();
        currencies.add(getCurrency());
        when(currencyApi.findCurrencies()).thenReturn(currencies);


        service.findByDate(LocalDate.now());

        verify(rateRepository, times(1)).findByStartDateOrEndDate(any(), any());
        verify(currencyApi, times(1)).findCurrencies();

    }

    private Rate getRate() {
        Rate rate = new Rate();
        rate.setId(7);
        rate.setBrandId(1);
        rate.setProductId(1);
        rate.setStartDate(LocalDate.now());
        rate.setEndDate(LocalDate.now().plusMonths(6));
        rate.setPrice(BigDecimal.valueOf(5000));
        rate.setCurrencyCode("EUR");
        return rate;
    }

    private Currency getCurrency() {
        Currency currency = new Currency();
        currency.setDecimals(2);
        currency.setSymbol("€");
        currency.setCode("EUR");
        return currency;
    }
}