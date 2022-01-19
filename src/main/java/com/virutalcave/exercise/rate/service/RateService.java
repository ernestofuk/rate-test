package com.virutalcave.exercise.rate.service;

import com.virutalcave.exercise.rate.model.api.CreateRateRequest;
import com.virutalcave.exercise.rate.model.api.FindRateResponse;
import com.virutalcave.exercise.rate.model.api.UpdateRateRequest;
import com.virutalcave.exercise.rate.model.entity.Rate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface RateService {

    public Mono<Void> createRate(CreateRateRequest request);

    public Mono<Void> updateRate(UpdateRateRequest request);

    public Mono<FindRateResponse> findById(Integer id);

    public Mono<Void> removeRate(Integer id);

    public Flux<FindRateResponse> findByDate(LocalDate date);

}
