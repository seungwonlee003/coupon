package com.example.coupon.application;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponRepository;
import com.example.coupon.domain.CouponStock;
import com.example.coupon.domain.CouponStockRepository;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponStockRepository couponStockRepository;

    public Coupon createCoupon(CouponRequest couponRequest) {
        // valid type
        if(!Coupon.validateType(couponRequest.getType(), couponRequest.getCount()))
            throw new IllegalArgumentException("Invalid coupon type");

        // valid discount_type
        if(!Coupon.validateDiscount_type(couponRequest.getDiscount_type(), couponRequest.getDiscount_amount()))
            throw new IllegalArgumentException("Invalid discount type");

        // valid start_date and end_date
        if(!Coupon.validateDate(couponRequest.getStart_date(), couponRequest.getEnd_date()))
            throw new IllegalArgumentException("Invalid date");

        // valid expire_minute
        if(!Coupon.validateExpireMinute(couponRequest.getExpireMinute()))
            throw new IllegalArgumentException("Invalid expire minute");

        Coupon coupon = new Coupon();
        coupon.setName(couponRequest.getName());
        coupon.setType(couponRequest.getType());
        coupon.setCount(couponRequest.getCount());
        coupon.setStart_date(couponRequest.getStart_date());
        coupon.setEnd_date(couponRequest.getEnd_date());
        coupon.setExpire_minute(couponRequest.getExpireMinute());
        coupon.setDiscount_type(couponRequest.getDiscount_type());
        coupon.setDiscount_amount(couponRequest.getDiscount_amount());
        coupon.setCreated_at(LocalDateTime.now());
        coupon.setUpdated_at(LocalDateTime.now());
        Coupon couponResponse = couponRepository.save(coupon);

        CouponStock couponStock = new CouponStock();
        couponStock.setCoupon_id(couponResponse.getId());
        couponStock.setCount(couponRequest.getCount());
        couponStock.setCreated_at(LocalDateTime.now());
        couponStock.setUpdated_at(LocalDateTime.now());
        couponStockRepository.save(couponStock);
        return couponResponse;
    }

    public Coupon updateCoupon(CouponUpdateRequest couponUpdateRequest){
        return couponRepository.save(new Coupon());
    }

    public Coupon deleteCoupon(CouponDeleteRequest couponDeleteRequest){
        return couponRepository.save(new Coupon());
    }
}
