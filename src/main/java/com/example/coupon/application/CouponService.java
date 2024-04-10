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
        // type and start_date cannot be changed and count can only be changed when type is 1
        // use load and save method for PUT
        Coupon coupon = couponRepository.findById(couponUpdateRequest.getCouponId()).orElse(null);
        if(coupon.getType() != couponUpdateRequest.getType())
            throw new IllegalArgumentException("Type cannot be changed");

        if(coupon.getType() == 0 && couponUpdateRequest.getCount() > 0 || couponUpdateRequest.getCount() < 0)
            throw new IllegalArgumentException("Count cannot be changed with type 0");

        if(!Coupon.validateDiscount_type(couponUpdateRequest.getDiscount_type(), couponUpdateRequest.getDiscount_amount()))
            throw new IllegalArgumentException("Invalid discount type");

        coupon.setName(couponUpdateRequest.getName());
        coupon.setCount(coupon.getCount() + couponUpdateRequest.getCount());
        coupon.setEnd_date(couponUpdateRequest.getEnd_date());
        coupon.setDiscount_type(couponUpdateRequest.getDiscount_type());
        coupon.setDiscount_amount(couponUpdateRequest.getDiscount_amount());
        coupon.setUpdated_at(LocalDateTime.now());

        CouponStock couponStock = couponStockRepository.findByCoupon_id(coupon.getId());
        couponStock.setCount(couponStock.getCount() + couponUpdateRequest.getCount());
        couponStock.setUpdated_at(LocalDateTime.now());
        couponStockRepository.save(couponStock);

        return couponRepository.save(new Coupon());
    }

    public Coupon deleteCoupon(CouponDeleteRequest couponDeleteRequest){
        Coupon coupon = couponRepository.findById(couponDeleteRequest.getCoupon_id()).orElse(null);
        if(coupon.getDeleted_at() != null)
            throw new IllegalArgumentException("Coupon has already been deleted");
        coupon.setDeleted_at(LocalDateTime.now());

        CouponStock couponStock = couponStockRepository.findByCoupon_id(coupon.getId());
        couponStock.setUpdated_at(LocalDateTime.now());
        couponStock.setDeleted_at(LocalDateTime.now());
        return couponRepository.save(coupon);
    }
}
