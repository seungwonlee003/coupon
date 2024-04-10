package com.example.coupon.presentation;

import com.example.coupon.application.CouponService;
import com.example.coupon.domain.Coupon;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/create")
    public Coupon createCoupon(@Valid CouponRequest couponRequest){
        return couponService.createCoupon(couponRequest);
    }

    @PutMapping("/update")
    public Coupon updateCoupon(@Valid CouponUpdateRequest couponUpdateRequest) {
        return couponService.updateCoupon(couponUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Coupon deleteCoupon(@Valid CouponDeleteRequest couponDeleteRequest){
        return couponService.deleteCoupon(couponDeleteRequest);
    }
}
