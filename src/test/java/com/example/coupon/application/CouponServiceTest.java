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

import static org.junit.jupiter.api.Assertions.*;
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
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(0).count(100).build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid coupon type"));
    }

    @Test
    public void createCoupon_WithInvalidTypeOneAndNegativeCount_ShouldFail(){
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(1).count(-100).build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid coupon type"));
    }

    @Test
    public void createCoupon_WithInvalidDiscountTypeZeroAndNegativeAmount_ShouldFail(){
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(0).count(0).discountType(0).discountAmount(-100).build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid discount type"));
    }

    @Test
    public void createCoupon_WithInvalidDiscountTypeOneAndAmountGreaterThan100_ShouldFail(){
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(0).count(0).discountType(1).discountAmount(1000).build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid discount type"));
    }

    @Test
    public void createCoupon_WithStartDateBeforeNow_ShouldFail() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(0).count(0).discountType(0).discountAmount(1)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });
        assertTrue(exception.getMessage().contains("Invalid date"));
    }

    @Test
    public void createCoupon_WithStartDateAfterEndDate_ShouldFail() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(0).count(0).discountType(0).discountAmount(1)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });
        assertTrue(exception.getMessage().contains("Invalid date"));
    }

    @Test
    public void createCoupon_WithExpireMinuteLessThanZero_ShouldFail() {
        // Arrange
        int expireMinute = -10;
        CouponRequest invalidCouponRequest = CouponRequest.builder()
                .type(0).count(0).discountType(0).discountAmount(1)
                .startDate(LocalDateTime.now().plusMinutes(1))
                .endDate(LocalDateTime.now().plusMinutes(2))
                .expireMinute(expireMinute)
                .build();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });
        assertTrue(exception.getMessage().contains("Invalid expire minute"));
    }





    // @ParameterizedTest
    //    @ValueSource(ints = {0, 1})
    //    public void createCoupon_WithInvalidTypeAndNegativeOrZeroCount_ShouldFail(int type) {
    //        CouponRequest invalidCouponRequest = CouponRequest.builder().type(type).count(-100).build();
    //        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
    //            couponService.createCoupon(invalidCouponRequest);
    //        });
    //
    //        assertTrue(exception.getMessage().contains("Invalid coupon type"));
    //    }
    //
    //    @ParameterizedTest
    //    @CsvSource({"0, -100", "1, 1000"})
    //    public void createCoupon_WithInvalidDiscountTypeAndAmount_ShouldFail(int discountType, double discountAmount) {
    //        CouponRequest invalidCouponRequest = CouponRequest.builder().discountType(discountType).discountAmount(discountAmount).build();
    //        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
    //            couponService.createCoupon(invalidCouponRequest);
    //        });
    //
    //        assertTrue(exception.getMessage().contains("Invalid discount type"));
    //    }

}
