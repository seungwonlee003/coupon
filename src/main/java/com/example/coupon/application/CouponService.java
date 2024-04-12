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
        validate(couponRequest);
        Coupon createdCoupon = couponRepository.save(couponRequest.toCoupon());
        createCouponStock(couponRequest, createdCoupon.getId());
        return createdCoupon;
    }

    @Transactional
    public Coupon updateCoupon(final CouponUpdateRequest couponUpdateRequest){
        Coupon coupon = findCouponById(couponUpdateRequest.getCouponId());
        validate(coupon, couponUpdateRequest);

        Coupon updateCouponInfo = updateCoupon(couponUpdateRequest, coupon);
        Coupon updatedCoupon = couponRepository.save(updateCouponInfo);

        updateCouponStockCount(updateCouponInfo.getId(), couponUpdateRequest.getCount());

        return updatedCoupon;
    }

    @Transactional
    public Coupon deleteCoupon(final CouponDeleteRequest couponDeleteRequest){
        Coupon coupon = findCouponById(couponDeleteRequest.getCouponId());
        if(coupon.getDeletedAt() != null)
            throw new IllegalArgumentException("Coupon has already been deleted");
        coupon.setDeletedAt(LocalDateTime.now());
        saveCouponStock(coupon.getId()); // Save CouponStock

        return couponRepository.save(coupon);
    }

    // createCoupon utility methods
    private void validate(final CouponRequest couponRequest){
        if(!validateType(couponRequest.getType(), couponRequest.getCount()))
            throw new IllegalArgumentException("Invalid coupon type");

        // valid discount_type
        if(!validateDiscountType(couponRequest.getDiscountType(), couponRequest.getDiscountAmount()))
            throw new IllegalArgumentException("Invalid discount type");

        // valid start_date and end_date
        if(!validateDate(couponRequest.getStartDate(), couponRequest.getEndDate()))
            throw new IllegalArgumentException("Invalid date");

        // valid expire_minute
        if(!validateExpireMinute(couponRequest.getExpireMinute()))
            throw new IllegalArgumentException("Invalid expire minute");
    }

    private void createCouponStock(final CouponRequest couponRequest, final long couponId) {
        CouponStock couponStock = new CouponStock();
        couponStock.setCouponId(couponId);
        couponStock.setCount(couponRequest.getCount());
        couponStockRepository.save(couponStock);
    }


    private void saveCouponStock(long couponId) {
        CouponStock couponStock = couponStockRepository.findByCouponId(couponId);
        couponStock.setDeletedAt(LocalDateTime.now());
        couponStockRepository.save(couponStock);
    }

    private Coupon findCouponById(long couponId) {
        return couponRepository.findById(couponId)
                // add exception
                .orElseThrow(IllegalArgumentException::new);
    }

    // updateCoupon utility methods
    private void validate(final Coupon coupon, final CouponUpdateRequest couponUpdateRequest){
        if(coupon.getType() != couponUpdateRequest.getType())
            throw new IllegalArgumentException("Type cannot be changed");

        if(!validateType(couponUpdateRequest.getType(), couponUpdateRequest.getCount()))
            throw new IllegalArgumentException("Type is invalid");

        if(couponUpdateRequest.getEnd_date().isBefore(couponUpdateRequest.getStart_date()))
            throw new IllegalArgumentException("End date cannot be before start date");

        if(!validateExpireMinute(couponUpdateRequest.getExpireMinute()))
            throw new IllegalArgumentException("Invalid expire minute");

        if(!validateDiscountType(couponUpdateRequest.getDiscount_type(), couponUpdateRequest.getDiscount_amount()))
            throw new IllegalArgumentException("Invalid discount type");
    }

    private Coupon updateCoupon(final CouponUpdateRequest couponUpdateRequest, final Coupon coupon){
        coupon.setName(couponUpdateRequest.getName());
        coupon.setCount(coupon.getCount() + couponUpdateRequest.getCount());
        coupon.setEndDate(couponUpdateRequest.getEnd_date());
        coupon.setDiscountType(couponUpdateRequest.getDiscount_type());
        coupon.setDiscountAmount(couponUpdateRequest.getDiscount_amount());
        return coupon;
    }

    private void updateCouponStockCount(long couponId, int count) {
        CouponStock couponStock = couponStockRepository.findByCouponId(couponId);
        couponStock.setCount(couponStock.getCount() + count);
        couponStockRepository.save(couponStock);
    }

    // Validation methods
    public boolean validateType(final int type, final int count){
        return type == 0 && count == 0 || type == 1 && count > 0;
    }

    public boolean validateDiscountType(final int discount_type, final double discount_amount){
        return discount_type == 0 && discount_amount > 0
                || (discount_type == 1 && discount_amount > 0 && discount_amount <= 100);
    }

    public boolean validateDate(final LocalDateTime startDate, final LocalDateTime endDate){
        return startDate.isAfter(LocalDateTime.now()) && startDate.isBefore(endDate);
    }

    public static boolean validateExpireMinute(final int expireMinute){
        return expireMinute > 0;
    }
}
