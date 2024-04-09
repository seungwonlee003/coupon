package com.example.coupon.presentation;

import com.example.coupon.application.CouponService;
import com.example.coupon.domain.Coupon;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    public Coupon createCoupon(CouponRequest couponRequest){
        return couponService.createCoupon(couponRequest);
    }

    public Coupon updateCoupon(CouponUpdateRequest couponUpdateRequest) {
        return couponService.updateCoupon(couponUpdateRequest);
    }

    public Coupon deleteCoupon(CouponDeleteRequest couponDeleteRequest){
        return couponService.deleteCoupon(couponDeleteRequest);
    }
}
