package com.example.coupon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// coupon delete
@Getter
public class CouponDeleteRequest {
    @NotNull
    private Long coupon_id;
}
