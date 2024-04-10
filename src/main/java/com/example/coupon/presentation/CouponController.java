package com.example.coupon.presentation;

import com.example.coupon.application.CouponService;
import com.example.coupon.domain.Coupon;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/create")
    public Coupon createCoupon(CouponRequest couponRequest){
        return couponService.createCoupon(couponRequest);
    }

    @PutMapping("/update")
    public Coupon updateCoupon(CouponUpdateRequest couponUpdateRequest) {
        return couponService.updateCoupon(couponUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Coupon deleteCoupon(CouponDeleteRequest couponDeleteRequest){
        return couponService.deleteCoupon(couponDeleteRequest);
    }
}
