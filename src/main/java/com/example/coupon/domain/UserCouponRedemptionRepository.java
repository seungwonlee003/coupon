package com.example.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponRedemptionRepository extends JpaRepository<UserCouponRedemption, Long> {
}
