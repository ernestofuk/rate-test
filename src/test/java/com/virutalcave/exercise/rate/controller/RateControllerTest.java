package com.virutalcave.exercise.rate.controller;

import com.virutalcave.exercise.rate.model.api.CreateRateRequest;
import com.virutalcave.exercise.rate.service.RateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(RateController.class)
public class RateControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RateService rateService;

    @Test
    public void createRate() {

      when(rateService.createRate(any())).thenReturn(Mono.just("test").then());

        CreateRateRequest request = new CreateRateRequest();
        request.setBrandId(1);
        request.setProductId(1);
        request.setCurrencyCode("EUR");
        request.setPrice(BigDecimal.valueOf(2000));
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now());

      webTestClient.post().uri("/v1/rate")
              .body(request, CreateRateRequest.class)
              .exchange()
              .expectStatus().isOk();


    }

}