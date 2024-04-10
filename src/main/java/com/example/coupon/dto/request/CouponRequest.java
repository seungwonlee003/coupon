package com.example.coupon.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// coupon create and update
public class CouponRequest {
    private String name;
    private int type;
    private int count;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int expire_minute;
    private int discount_type;
    private double discount_amount;
}
