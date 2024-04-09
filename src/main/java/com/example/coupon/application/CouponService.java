package com.example.coupon.application;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponRepository;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    public Coupon createCoupon(CouponRequest couponRequest) {
        return couponRepository.save(new Coupon());
    }

    public Coupon updateCoupon(CouponUpdateRequest couponUpdateRequest){
        return couponRepository.save(new Coupon());
    }

    public Coupon deleteCoupon(CouponDeleteRequest couponDeleteRequest){
        return couponRepository.save(new Coupon());
    }
}
