package com.example.coupon.domain;

import com.example.coupon.dto.response.CouponResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponStockRepository extends JpaRepository<CouponStock, Long> {
    CouponStock findByCouponId(Long id);

    // should i use a projection here or jpql constructor expression?
    @Query("SELECT NEW com.example.coupon.dto.response.CouponResponse(c, cs) " +
            "FROM Coupon c " +
            "JOIN CouponStock cs ON c.id = cs.couponId")
    List<CouponResponse> findAllCouponsWithStock();
}
