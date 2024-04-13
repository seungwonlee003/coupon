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
    public void issueUserCoupon(final UserCouponIssueRequest request){
        userCouponService.issueUserCoupon(request);
    }

    public UserCouponReadResponse readUserCoupon(final UserCouponReadRequest userCouponReadRequest){
        return userCouponService.readUserCoupon(userCouponReadRequest);
    }

    public UserCouponResponse useUserCoupon(final UserCouponUsageRequest userCouponUsageRequest){
        return userCouponService.useUserCoupon(userCouponUsageRequest);
    }

    public UserCouponResponse cancelUserCoupon(final UserCouponUpdateRequest userCouponUpdateRequest){
        return userCouponService.cancelUserCoupon(userCouponUpdateRequest);
    }

    public UserCouponResponse deleteUserCoupon(final UserCouponUpdateRequest userCouponUpdateRequest){
        return userCouponService.deleteUserCoupon(userCouponUpdateRequest);
    }
}
