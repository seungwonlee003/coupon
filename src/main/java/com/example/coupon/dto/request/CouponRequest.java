package com.example.coupon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

import java.time.LocalDateTime;

@Getter
// coupon create and update
public class CouponRequest {
    @NotNull
    private String name;
    @NotNull
    private int type;
    @NotNull
    private int count;
    @NotNull
    private LocalDateTime start_date;
    @NotNull
    private LocalDateTime end_date;
    @NotNull
    private int expire_minute;
    @NotNull
    private int discount_type;
    @NotNull
    private double discount_amount;
}
