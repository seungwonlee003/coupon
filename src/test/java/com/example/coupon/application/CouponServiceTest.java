package com.example.coupon.application;

import com.example.coupon.domain.coupon.Coupon;
import com.example.coupon.domain.coupon.CouponRepository;
import com.example.coupon.domain.coupon.CouponStockRepository;
import com.example.coupon.dto.request.CouponRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponStockRepository couponStockRepository;

    @InjectMocks
    private CouponService couponService;

    private CouponRequest createValidCouponRequest() {
        return CouponRequest.builder()
                .type(0)
                .count(0)
                .discountType(0)
                .discountAmount(100)
                .startDate(LocalDateTime.now().plusDays(1))
                .endDate(LocalDateTime.now().plusDays(30))
                .expireMinute(60)
                .build();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 100",
            "0, -100",
            "1, 0",
            "1, -100"
    })
    public void createCoupon_WithInvalidTypeAndCount_ShouldFail(int type, int count) {
        CouponRequest invalidCouponRequest = createValidCouponRequest().toBuilder()
                .type(type)
                .count(count)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid coupon type"));
        verifyNoInteractions(couponRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "0, -100",
            "0, 0",
            "1, 101",
            "1, 0"
    })
    public void createCoupon_WithInvalidDiscountType_ShouldFail(int discountType, int discountAmount) {
        CouponRequest invalidCouponRequest = createValidCouponRequest().toBuilder()
                .discountType(discountType)
                .discountAmount(discountAmount)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid discount type"));
        verifyNoInteractions(couponRepository);
    }

    @Test
    public void createCoupon_WithStartDateBeforeNow_ShouldFail() {
        // Arrange
        CouponRequest invalidCouponRequest = createValidCouponRequest().toBuilder()
                .startDate(LocalDateTime.now().minusDays(1))
                .build();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });
        assertTrue(exception.getMessage().contains("Invalid date"));
        verifyNoInteractions(couponRepository);
    }

    @Test
    public void createCoupon_WithStartDateAfterEndDate_ShouldFail() {
        CouponRequest invalidCouponRequest = createValidCouponRequest().toBuilder()
                .startDate(LocalDateTime.now().plusDays(2))
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });
        assertTrue(exception.getMessage().contains("Invalid date"));
        verifyNoInteractions(couponRepository);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0})
    public void createCoupon_WithInvalidExpireMinute_ShouldFail(int expireMinute) {
        CouponRequest invalidCouponRequest = createValidCouponRequest().toBuilder()
                .expireMinute(expireMinute)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(invalidCouponRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid expire minute"));
        verifyNoInteractions(couponRepository);
    }


}
