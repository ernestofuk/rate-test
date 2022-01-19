package com.virutalcave.exercise.rate.repository;

import com.virutalcave.exercise.rate.model.entity.Rate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface RateRepository extends ReactiveCrudRepository<Rate, Integer> {

    Flux<Rate> findByStartDateOrEndDate(LocalDate startDate, LocalDate endDate);

}
