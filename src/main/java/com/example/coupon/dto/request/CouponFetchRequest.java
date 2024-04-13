package com.example.coupon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponFetchRequest {
    @NotNull
    private int limit;
    private Long lastCouponId;
}
