package com.example.coupon.dto.request;

import com.example.coupon.domain.coupon.Coupon;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CouponUpdateRequest {
    @NotNull
    private Long couponId;

    @NotNull
    private String name;

    @NotNull
    private int type;

    @NotNull
    private int count;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private int expireMinute;

    @NotNull
    private int discountType;

    @NotNull
    private double discountAmount;

    public CouponRequest toCouponRequest() {
        return CouponRequest.builder()
                .name(name)
                .type(type)
                .count(count)
                .startDate(startDate)
                .endDate(endDate)
                .expireMinute(expireMinute)
                .discountType(discountType)
                .discountAmount(discountAmount)
                .build();
    }

    public Coupon overwriteToCoupon(Coupon coupon){
        coupon.setName(name);
        coupon.setType(type);
        coupon.setCount(count);
        coupon.setStartDate(startDate);
        coupon.setEndDate(endDate);
        coupon.setExpireMinute(expireMinute);
        coupon.setDiscountType(discountType);
        coupon.setDiscountAmount(discountAmount);
        return coupon;
    }

}
