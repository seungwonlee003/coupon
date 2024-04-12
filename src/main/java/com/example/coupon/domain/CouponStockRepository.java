package com.example.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponStockRepository extends JpaRepository<CouponStock, Long> {
    // for separately retrieving coupon stock detached from coupon entity
    @Query("SELECT cs FROM CouponStock cs WHERE cs.coupon.id = :couponId")
    public CouponStock findByCouponId(Long couponId);
}
