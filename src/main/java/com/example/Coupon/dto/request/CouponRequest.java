package com.example.Coupon.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// coupon create and update
public class CouponRequest {
    private String name;
    private int type;
    private int count;
    private LocalDateTime Start_date;
    private LocalDateTime End_date;
    private int expireMinute;
    private int discount_type;
    private double discount_amount;
}
