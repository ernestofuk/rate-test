package com.virutalcave.exercise.rate.service;

import com.virutalcave.exercise.rate.client.CurrencyApi;
import com.virutalcave.exercise.rate.mapper.RateMapper;
import com.virutalcave.exercise.rate.model.api.CreateRateRequest;
import com.virutalcave.exercise.rate.model.api.FindRateResponse;
import com.virutalcave.exercise.rate.model.api.UpdateRateRequest;
import com.virutalcave.exercise.rate.model.entity.Rate;
import com.virutalcave.exercise.rate.model.thirdparties.currency.Currency;
import com.virutalcave.exercise.rate.repository.RateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {

    private final CurrencyApi currencyApi;

    private final RateRepository rateRepository;

    private final RateMapper rateMapper;

    public RateServiceImpl(CurrencyApi currencyApi, RateRepository rateRepository, RateMapper rateMapper) {
        this.currencyApi = currencyApi;
        this.rateRepository = rateRepository;
        this.rateMapper = rateMapper;
    }

    @Override
    public Mono<Void> createRate(CreateRateRequest request) {
        return rateRepository.save(rateMapper.createRequestToRate(request))
                .then();
    }

    @Override
    public Mono<Void> updateRate(UpdateRateRequest request) {
        return rateRepository.save(rateMapper.updateRequestToRate(request))
                .then();
    }

    @Override
    public Mono<FindRateResponse> findById(Integer id) {
        return rateRepository.findById(id)
                .map(rate -> rateMapper
                        .rateToResponse(rate, currencyApi.findByCode(rate.getCurrencyCode())));
    }

    @Override
    public Mono<Void> removeRate(Integer id) {
        return rateRepository.deleteById(id);
    }

    @Override
    public Flux<FindRateResponse> findByDate(LocalDate date) {
        Map<String, Currency> currencyMap = currencyApi.findCurrencies()
                .stream().collect(Collectors.toMap(Currency::getCode, currency -> currency));
        return rateRepository.findByStartDateOrEndDate(date, date)
                .map(rate -> {
                    Currency currency = currencyMap.get(rate.getCurrencyCode());
                    return rateMapper.rateToResponse(rate, currency);
                });
    }

}
