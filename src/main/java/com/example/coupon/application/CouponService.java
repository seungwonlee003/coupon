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
        validate(coupon);
        coupon.setDeletedAt(LocalDateTime.now());
        saveCouponStock(coupon.getId()); // Save CouponStock

        return couponRepository.save(coupon);
    }

    // createCoupon utility methods
    private void validate(final CouponRequest couponRequest){
        if(!isValidType(couponRequest.getType(), couponRequest.getCount()))
            throw new IllegalArgumentException("Invalid coupon type");

        // valid discount_type
        if(!isValidDiscountType(couponRequest.getDiscountType(), couponRequest.getDiscountAmount()))
            throw new IllegalArgumentException("Invalid discount type");

        // valid start_date and end_date
        if(!isValidDate(couponRequest.getStartDate(), couponRequest.getEndDate()))
            throw new IllegalArgumentException("Invalid date");

        // valid expire_minute
        if(!isValidExpireMinute(couponRequest.getExpireMinute()))
            throw new IllegalArgumentException("Invalid expire minute");
    }

    private void createCouponStock(final CouponRequest couponRequest, final Long couponId) {
        CouponStock couponStock = new CouponStock();
        couponStock.setCouponId(couponId);
        couponStock.setCount(couponRequest.getCount());
        couponStockRepository.save(couponStock);
    }

    // updateCoupon utility methods
    private void validate(final Coupon coupon, final CouponUpdateRequest couponUpdateRequest){
        if(coupon.getType() != couponUpdateRequest.getType())
            throw new IllegalArgumentException("Type cannot be changed");

        if(!isValidType(couponUpdateRequest.getType(), couponUpdateRequest.getCount()))
            throw new IllegalArgumentException("Type is invalid");

        if(couponUpdateRequest.getEnd_date().isBefore(couponUpdateRequest.getStart_date()))
            throw new IllegalArgumentException("End date cannot be before start date");

        if(!isValidExpireMinute(couponUpdateRequest.getExpireMinute()))
            throw new IllegalArgumentException("Invalid expire minute");

        if(!isValidDiscountType(couponUpdateRequest.getDiscount_type(), couponUpdateRequest.getDiscount_amount()))
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

    private void updateCouponStockCount(Long couponId, int count) {
        CouponStock couponStock = couponStockRepository.findByCouponId(couponId);
        couponStock.setCount(couponStock.getCount() + count);
        couponStockRepository.save(couponStock);
    }

    // deleteCoupon utility methods
    private void saveCouponStock(Long couponId) {
        CouponStock couponStock = couponStockRepository.findByCouponId(couponId);
        couponStock.setDeletedAt(LocalDateTime.now());
        couponStockRepository.save(couponStock);
    }

    private Coupon findCouponById(Long couponId) {
        return couponRepository.findById(couponId)
                // add exception
                .orElseThrow(IllegalArgumentException::new);
    }

    private void validate(Coupon coupon){
        if(coupon.getDeletedAt() != null)
            throw new IllegalArgumentException("Coupon has already been deleted");
    }

    // Validation methods
    public boolean isValidType(final int type, final int count){
        return (type == 0 && count == 0) || (type == 1 && count > 0);
    }

    public boolean isValidDiscountType(final int discount_type, final double discount_amount){
        return (discount_type == 0 && discount_amount > 0)
                || (discount_type == 1 && discount_amount > 0 && discount_amount <= 100);
    }

    public boolean isValidDate(final LocalDateTime startDate, final LocalDateTime endDate){
        return startDate.isAfter(LocalDateTime.now()) && startDate.isBefore(endDate);
    }

    public static boolean isValidExpireMinute(final int expireMinute){
        return expireMinute > 0;
    }
}
