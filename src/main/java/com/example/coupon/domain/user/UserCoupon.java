package com.example.coupon.domain.user;

import com.example.coupon.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "coupon_id", nullable = false)
    private Long couponId;

    @Column(name = "product_id", nullable = true)
    private Long productId;

    @Column(name = "give_date", nullable = false)
    private LocalDateTime giveDate;

    @Column(name = "used_date", nullable = true)
    private LocalDateTime usedDate;

    @Column(name = "expire_date", nullable = false)
    private LocalDateTime expireDate;

    @Column(name = "discount_type", nullable = false)
    private int discountType;

    @Column(name = "discount_amount", nullable = false)
    private double discountAmount;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
}
