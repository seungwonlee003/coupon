package com.example.coupon.application;

import com.example.coupon.domain.coupon.Coupon;
import com.example.coupon.domain.coupon.CouponRepository;
import com.example.coupon.domain.coupon.CouponStock;
import com.example.coupon.domain.coupon.CouponStockRepository;
import com.example.coupon.dto.request.CouponFetchRequest;
import com.example.coupon.dto.request.CouponRequest;
import com.example.coupon.dto.response.CouponResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {
    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponStockRepository couponStockRepository;

    @InjectMocks
    private CouponService couponService;

    @Test
    public void createCoupon_WithInvalidTypeZeroAndNonZeroCount_ShouldFail(){
        CouponRequest invalidCouponRequest = CouponRequest.builder().type(0).count(10).build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assert(exception.getMessage().contains("Invalid coupon type"));
    }

    @Test
    public void createCoupon_WithInvalidTypeOneAndNegativeCount_ShouldFail(){

    }
}
