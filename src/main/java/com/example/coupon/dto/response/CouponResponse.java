package com.example.coupon.dto.response;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponStock;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CouponResponse {
    private Long id;

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

    public CouponResponse(Coupon coupon, CouponStock couponStock) {
        this.id = coupon.getId();
        this.name = coupon.getName();
        this.type = coupon.getType();
        this.startDate = coupon.getStartDate();
        this.endDate = coupon.getEndDate();
        this.expireMinute = coupon.getExpireMinute();
        this.discountType = coupon.getDiscountType();
        this.discountAmount = coupon.getDiscountAmount();
        this.createdAt = coupon.getCreatedAt();
        this.updatedAt = coupon.getUpdatedAt();
        this.deletedAt = coupon.getDeletedAt();
        this.stockCount = couponStock.getCount();
    }
}
