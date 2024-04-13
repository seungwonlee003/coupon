package com.example.coupon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CouponFetchRequest {
    @NotNull
    private int limit;
    private Long lastCouponId;
}
