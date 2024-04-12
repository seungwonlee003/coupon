package com.example.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponStockRepository extends JpaRepository<CouponStock, Long> {
    public CouponStock findByCouponId(Long couponId);
}
