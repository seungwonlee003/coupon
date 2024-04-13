package com.example.coupon.application;

import com.example.coupon.domain.user.UserCoupon;
import com.example.coupon.domain.user.UserCouponRepository;
import com.example.coupon.dto.request.UserCouponReadRequest;
import com.example.coupon.dto.request.UserCouponIssueRequest;
import com.example.coupon.dto.request.UserCouponUpdateRequest;
import com.example.coupon.dto.request.UserCouponUsageRequest;
import com.example.coupon.dto.response.UserCouponReadResponse;
import com.example.coupon.dto.response.UserCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCouponService {
    private final UserCouponRepository userCouponRepository;
    public void issueCoupon(UserCouponIssueRequest request){
        userCouponRepository.save(new UserCoupon());
    }

    public UserCouponReadResponse readCoupon(UserCouponReadRequest userCouponReadRequest){
        return new UserCouponReadResponse();
    }

    public UserCouponResponse useCoupon(UserCouponUsageRequest userCouponUsageRequest){
        return new UserCouponResponse();
    }

    public UserCouponResponse cancelCoupon(UserCouponUpdateRequest userCouponUpdateRequest){
        return new UserCouponResponse();
    }

    public UserCouponResponse deleteCoupon(UserCouponUpdateRequest userCouponUpdateRequest){
        return new UserCouponResponse();
    }
}
