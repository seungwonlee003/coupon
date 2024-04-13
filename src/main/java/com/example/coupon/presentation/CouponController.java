package com.example.coupon.presentation;

import com.example.coupon.application.CouponService;
import com.example.coupon.domain.coupon.Coupon;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponFetchRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import com.example.coupon.dto.response.CouponResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/get")
    public List<CouponResponse> getAllCoupons(@Valid final CouponFetchRequest couponFetchRequest){
        return couponService.getAllCoupons(couponFetchRequest);
    }

    @PostMapping("/create")
    public Coupon createCoupon(@Valid final CouponRequest couponRequest){
        return couponService.createCoupon(couponRequest);
    }

    @PutMapping("/update")
    public Coupon updateCoupon(@Valid final CouponUpdateRequest couponUpdateRequest) {
        return couponService.updateCoupon(couponUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Coupon deleteCoupon(@Valid final CouponDeleteRequest couponDeleteRequest){
        return couponService.deleteCoupon(couponDeleteRequest);
    }
}
