package com.example.coupon.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CouponUpdateRequest {
    private Long couponId;
    private String name;
    private int type;
    private int count;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int expireMinute;
    private int discount_type;
    private double discount_amount;
}