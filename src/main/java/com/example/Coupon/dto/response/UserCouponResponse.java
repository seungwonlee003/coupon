package com.example.Coupon.dto.response;

import java.time.LocalDateTime;

// UserCoupon usage, update, delete
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
