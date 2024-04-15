package com.example.coupon.dto.request;

import com.example.coupon.domain.coupon.Coupon;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
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
