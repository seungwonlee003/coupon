package com.example.coupon.dto.response;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {
    private Long id;

    private CouponStock couponStock;

    private String name;

    private int type;

    private int count;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int expireMinute;

    private int discountType;

    private double discountAmount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private int stockCount;

    public static CouponResponse toCouponResponse(Coupon coupon) {
        return CouponResponse.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .type(coupon.getType())
                .count(coupon.getCount())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .expireMinute(coupon.getExpireMinute())
                .discountType(coupon.getDiscountType())
                .discountAmount(coupon.getDiscountAmount())
                .createdAt(coupon.getCreatedAt())
                .updatedAt(coupon.getUpdatedAt())
                .deletedAt(coupon.getDeletedAt())
                .stockCount(coupon.getCouponStock().getCount())
                .build();
    }
}
