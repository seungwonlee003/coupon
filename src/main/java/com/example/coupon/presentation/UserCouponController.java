package com.example.coupon.presentation;

import com.example.coupon.application.UserCouponService;
import com.example.coupon.dto.request.UserCouponReadRequest;
import com.example.coupon.dto.request.UserCouponIssueRequest;
import com.example.coupon.dto.request.UserCouponUpdateRequest;
import com.example.coupon.dto.request.UserCouponUsageRequest;
import com.example.coupon.dto.response.UserCouponReadResponse;
import com.example.coupon.dto.response.UserCouponResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserCouponController {
    private final UserCouponService userCouponService;
    public void issueCoupon(UserCouponIssueRequest request){
        userCouponService.issueCoupon(request);
    }

    public UserCouponReadResponse readCoupon(UserCouponReadRequest userCouponReadRequest){
        return userCouponService.readCoupon(userCouponReadRequest);
    }

    public UserCouponResponse useCoupon(UserCouponUsageRequest userCouponUsageRequest){
        return userCouponService.useCoupon(userCouponUsageRequest);
    }

    public UserCouponResponse cancelCoupon(UserCouponUpdateRequest userCouponUpdateRequest){
        return userCouponService.cancelCoupon(userCouponUpdateRequest);
    }

    public UserCouponResponse deleteCoupon(UserCouponUpdateRequest userCouponUpdateRequest){
        return userCouponService.deleteCoupon(userCouponUpdateRequest);
    }
}
