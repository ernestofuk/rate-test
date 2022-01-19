package com.virutalcave.exercise.rate.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("t_rates")
@Data
public class Rate {

    @Id
    @Column("id")
    private Integer id;

    @Column("brand_id")
    private Integer brandId;

    @Column("product_id")
    private Integer productId;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("price")
    private BigDecimal price;

    @Column("currency_code")
    private String currencyCode;
}
