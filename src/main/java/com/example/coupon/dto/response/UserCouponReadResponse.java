package com.example.coupon.dto.response;

import com.example.coupon.domain.coupon.Coupon;

import java.time.LocalDateTime;

public class UserCouponReadResponse {
    private Long id;
    private Long userId;
    private Long couponId;
    private Long productId;
    private LocalDateTime giveDate;
    private LocalDateTime useDate;
    private LocalDateTime expireDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Coupon coupon;
}
