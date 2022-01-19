package com.virutalcave.exercise.rate.controller;

import com.virutalcave.exercise.rate.model.api.CreateRateRequest;
import com.virutalcave.exercise.rate.model.api.FindRateResponse;
import com.virutalcave.exercise.rate.model.api.UpdateRateRequest;
import com.virutalcave.exercise.rate.model.entity.Rate;
import com.virutalcave.exercise.rate.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/rate")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping
    public Mono<Void> createRate(@RequestBody CreateRateRequest request) {
        return rateService.createRate(request);
    }

    @PatchMapping
    public Mono<Void> updateRate(@RequestBody UpdateRateRequest request) {
        return rateService.updateRate(request);
    }

    @GetMapping("/{id}")
    public Mono<FindRateResponse> findById(@PathVariable Integer id) {
        return rateService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> removeRate(@PathVariable Integer id) {
        return rateService.removeRate(id);
    }

    @GetMapping("/{date}")
    public Flux<FindRateResponse> findByDate(@PathVariable LocalDate date) {
        return rateService.findByDate(date);
    }

}
