package com.example.coupon.dto.response;

import com.example.coupon.domain.Coupon;

import java.time.LocalDateTime;

// UserCoupon usage, cancel, delete
public class UserCouponResponse {
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
}
