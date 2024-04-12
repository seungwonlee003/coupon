package com.example.coupon.application;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponRepository;
import com.example.coupon.domain.CouponStock;
import com.example.coupon.domain.CouponStockRepository;
import com.example.coupon.dto.request.CouponDeleteRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.request.CouponUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponStockRepository couponStockRepository;

    @Transactional
    public Coupon createCoupon(final CouponRequest couponRequest) {
        // valid type
        if(!Coupon.validateType(couponRequest.getType(), couponRequest.getCount()))
            throw new IllegalArgumentException("Invalid coupon type");

        // valid discount_type
        if(!Coupon.validateDiscountType(couponRequest.getDiscount_type(), couponRequest.getDiscount_amount()))
            throw new IllegalArgumentException("Invalid discount type");

        // valid start_date and end_date
        if(!Coupon.validateDate(couponRequest.getStart_date(), couponRequest.getEnd_date()))
            throw new IllegalArgumentException("Invalid date");

        // valid expire_minute
        if(!Coupon.validateExpireMinute(couponRequest.getExpire_minute()))
            throw new IllegalArgumentException("Invalid expire minute");

        Coupon coupon = new Coupon();
        coupon.setName(couponRequest.getName());
        coupon.setType(couponRequest.getType());
        coupon.setCount(couponRequest.getCount());
        coupon.setStartDate(couponRequest.getStart_date());
        coupon.setEndDate(couponRequest.getEnd_date());
        coupon.setExpireMinute(couponRequest.getExpire_minute());
        coupon.setDiscountType(couponRequest.getDiscount_type());
        coupon.setDiscountAmount(couponRequest.getDiscount_amount());
        coupon.setCreatedAt(LocalDateTime.now());
        coupon.setUpdatedAt(LocalDateTime.now());
        Coupon couponResponse = couponRepository.save(coupon);

        CouponStock couponStock = new CouponStock();
        couponStock.setCoupon_id(couponResponse.getId());
        couponStock.setCount(couponRequest.getCount());
        couponStock.setCreated_at(LocalDateTime.now());
        couponStock.setUpdated_at(LocalDateTime.now());
        couponStockRepository.save(couponStock);
        return couponResponse;
    }

    @Transactional
    public Coupon updateCoupon(final CouponUpdateRequest couponUpdateRequest){
        // type and start_date cannot be changed and count can only be changed when type is 1
        // use load and save method for PUT
        Coupon coupon = couponRepository.findById(couponUpdateRequest.getCouponId())
                .orElseThrow(null);
        if(coupon.getType() != couponUpdateRequest.getType())
            throw new IllegalArgumentException("Type cannot be changed");

        if(!Coupon.validateType(couponUpdateRequest.getType(), couponUpdateRequest.getCount()))
            throw new IllegalArgumentException("Type is invalid");

        if(couponUpdateRequest.getEnd_date().isBefore(couponUpdateRequest.getStart_date()))
            throw new IllegalArgumentException("End date cannot be before start date");

        if(!Coupon.validateExpireMinute(couponUpdateRequest.getExpireMinute()))
            throw new IllegalArgumentException("Invalid expire minute");

        if(!Coupon.validateDiscountType(couponUpdateRequest.getDiscount_type(), couponUpdateRequest.getDiscount_amount()))
            throw new IllegalArgumentException("Invalid discount type");

        coupon.setName(couponUpdateRequest.getName());
        coupon.setCount(coupon.getCount() + couponUpdateRequest.getCount());
        coupon.setEndDate(couponUpdateRequest.getEnd_date());
        coupon.setDiscountType(couponUpdateRequest.getDiscount_type());
        coupon.setDiscountAmount(couponUpdateRequest.getDiscount_amount());
        coupon.setUpdatedAt(LocalDateTime.now());
        Coupon couponResponse = couponRepository.save(coupon);

        CouponStock couponStock = couponStockRepository.findByCoupon_id(couponResponse.getId());
        couponStock.setCount(couponStock.getCount() + couponUpdateRequest.getCount());
        couponStock.setUpdated_at(LocalDateTime.now());
        couponStockRepository.save(couponStock);

        return couponResponse;
    }

    @Transactional
    public Coupon deleteCoupon(final CouponDeleteRequest couponDeleteRequest){
        Coupon coupon = couponRepository.findById(couponDeleteRequest.getCoupon_id())
                .orElseThrow();
        if(coupon.getDeletedAt() != null)
            throw new IllegalArgumentException("Coupon has already been deleted");
        coupon.setDeletedAt(LocalDateTime.now());

        CouponStock couponStock = couponStockRepository.findByCoupon_id(coupon.getId());
        couponStock.setUpdated_at(LocalDateTime.now());
        couponStock.setDeleted_at(LocalDateTime.now());
        couponStockRepository.save(couponStock);
        return couponRepository.save(coupon);
    }
}
