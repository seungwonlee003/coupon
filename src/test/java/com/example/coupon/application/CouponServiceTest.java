package com.example.coupon.application;

import com.example.coupon.domain.coupon.Coupon;
import com.example.coupon.domain.coupon.CouponRepository;
import com.example.coupon.domain.coupon.CouponStock;
import com.example.coupon.domain.coupon.CouponStockRepository;
import com.example.coupon.dto.request.CouponFetchRequest;
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
    public void testGetAllCoupons_WithNullLastCouponId_ShouldReturnCoupons() {
        CouponFetchRequest couponFetchRequest = new CouponFetchRequest();
        couponFetchRequest.setLimit(2);
        Slice<Coupon> mockSlice = createMockCouponSlice(2);

        PageRequest pageRequest = PageRequest.of(0, couponFetchRequest.getLimit(), Sort.by(Sort.Direction.DESC, "id"));

        when(couponRepository.findFirstNCouponsWithStock(pageRequest)).thenReturn(mockSlice);

        // Act
        List<CouponResponse> result = couponService.getAllCoupons(couponFetchRequest);

        // Assert
        assertEquals(mockSlice.getContent().size(), result.size());
        // You can add more assertions here to verify the result
    }

    @Test
    public void testGetAllCoupons_WithNonNullLastCouponId_ShouldReturnCoupons() {
        CouponFetchRequest couponFetchRequest = new CouponFetchRequest();
        couponFetchRequest.setLastCouponId(0L);
        couponFetchRequest.setLimit(4);
        Slice<Coupon> mockSlice = createMockCouponSlice(4);

        PageRequest pageRequest = PageRequest.of(0, couponFetchRequest.getLimit(), Sort.by(Sort.Direction.DESC, "id"));

        when(couponRepository.findNextNCouponsWithStock(0L, pageRequest)).thenReturn(mockSlice);

        // Act
        List<CouponResponse> result = couponService.getAllCoupons(couponFetchRequest);

        // Assert
        assertEquals(mockSlice.getContent().size(), result.size());
        // You can add more assertions here to verify the result
    }

    // edge case: when the limit is 0 or negative
    // boundary case: when last coupon ID is last coupon in the list
    // error handling: when repo method returns empty or null slice

    private Slice<Coupon> createMockCouponSlice(int num) {
        List<Coupon> coupons = new ArrayList<>();
        // Add some mock Coupon objects to the list
        for (int i = 1; i <= num; i++) {
            Coupon mockCoupon = createMockCoupon(i);
            coupons.add(mockCoupon);
        }
        return new SliceImpl<>(coupons);
    }

    private Coupon createMockCoupon(long id) {
        Coupon mockCoupon = new Coupon();
        mockCoupon.setId(id);
        mockCoupon.setCouponStock(new CouponStock());
        mockCoupon.setName("");
        mockCoupon.setType(1);
        mockCoupon.setCount(1);
        mockCoupon.setStartDate(LocalDateTime.now());
        mockCoupon.setEndDate(LocalDateTime.now());
        mockCoupon.setExpireMinute(1);
        mockCoupon.setDiscountType(1);
        mockCoupon.setDiscountAmount(1);
        return mockCoupon;
    }


}
