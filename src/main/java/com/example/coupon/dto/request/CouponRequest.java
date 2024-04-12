package com.example.coupon.dto.request;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponStock;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// coupon create and update
public class CouponRequest {
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

    public void validate(){
        if(!Coupon.validateType(type, count))
            throw new IllegalArgumentException("Invalid coupon type");

        // valid discount_type
        if(!Coupon.validateDiscountType(discountType, discountAmount))
            throw new IllegalArgumentException("Invalid discount type");

        // valid start_date and end_date
        if(!Coupon.validateDate(startDate, endDate))
            throw new IllegalArgumentException("Invalid date");

        // valid expire_minute
        if(!Coupon.validateExpireMinute(expireMinute))
            throw new IllegalArgumentException("Invalid expire minute");
    }

    public Coupon toCoupon(){
        return Coupon.builder()
                .name(name)
                .type(type)
                .count(count)
                .startDate(startDate)
                .endDate(endDate)
                .expireMinute(expireMinute)
                .discountType(discountType)
                .discountAmount(discountAmount).build();
    }

}
