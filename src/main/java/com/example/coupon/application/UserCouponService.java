package com.example.coupon.application;

import com.example.coupon.domain.coupon.Coupon;
import com.example.coupon.domain.coupon.CouponRepository;
import com.example.coupon.domain.coupon.CouponStock;
import com.example.coupon.domain.coupon.CouponStockRepository;
import com.example.coupon.domain.user.UserCoupon;
import com.example.coupon.domain.user.UserCouponRepository;
import com.example.coupon.dto.request.UserCouponReadRequest;
import com.example.coupon.dto.request.UserCouponIssueRequest;
import com.example.coupon.dto.request.UserCouponUpdateRequest;
import com.example.coupon.dto.request.UserCouponUsageRequest;
import com.example.coupon.dto.response.UserCouponReadResponse;
import com.example.coupon.dto.response.UserCouponResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserCouponService {
    private final CouponRepository couponRepository;
    private final CouponStockRepository couponStockRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public void issueUserCoupon(final UserCouponIssueRequest request){
        Coupon coupon = couponRepository.findById(request.getCouponId())
                // add exception for coupon not found exception
                .orElseThrow(IllegalArgumentException::new);

        // check validity of the coupon
        LocalDateTime now = LocalDateTime.now();
        if(coupon.getStartDate().isAfter(now)
                || coupon.getEndDate().isAfter(now)){
            throw new IllegalArgumentException("Invalid coupon");
        }

        // unique constraint check by adding coupon to userCoupon repo
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserId(request.getUserId());
        userCoupon.setCouponId(request.getCouponId());
        userCoupon.setGiveDate(now);
        userCoupon.setExpireDate(coupon.getStartDate().plusMinutes(coupon.getExpireMinute()));
        userCoupon.setDiscountType(coupon.getDiscountType());
        userCoupon.setDiscountAmount(coupon.getDiscountAmount());

        try {
            // Attempt to save the UserCoupon entity
            // used saveAndFlush to prevent write-behind
            userCouponRepository.saveAndFlush(userCoupon);
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation
            throw new IllegalArgumentException("Cannot issue already redeemed coupon");
        }

        // decrease the stock
        if(coupon.getCouponStock().getCount() > 0){
            coupon.getCouponStock().setCount(coupon.getCouponStock().getCount() - 1);
        }
    }

    public UserCouponReadResponse readUserCoupon(UserCouponReadRequest userCouponReadRequest){
        return new UserCouponReadResponse();
    }

    public UserCouponResponse useUserCoupon(UserCouponUsageRequest userCouponUsageRequest){
        return new UserCouponResponse();
    }

    public UserCouponResponse cancelUserCoupon(UserCouponUpdateRequest userCouponUpdateRequest){
        return new UserCouponResponse();
    }

    public UserCouponResponse deleteUserCoupon(UserCouponUpdateRequest userCouponUpdateRequest){
        return new UserCouponResponse();
    }
}
