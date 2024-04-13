package com.example.coupon.dto.request;

import lombok.Getter;

// userCoupon issue
@Getter
public class UserCouponIssueRequest {
    private Long userId;
    private Long couponId;
}
