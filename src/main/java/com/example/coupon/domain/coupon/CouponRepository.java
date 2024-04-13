package com.example.coupon.domain.coupon;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("SELECT c FROM Coupon c JOIN FETCH c.couponStock WHERE c.id <= :lastCouponId")
    Slice<Coupon> findNextNCouponsWithStock(Long lastCouponId, Pageable pageable);

    @Query("SELECT c FROM Coupon c JOIN FETCH c.couponStock")
    Slice<Coupon> findFirstNCouponsWithStock(Pageable pageable);
}
