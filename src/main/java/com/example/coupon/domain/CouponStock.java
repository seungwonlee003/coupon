package com.example.coupon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponStock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "coupon_id", nullable = false)
    private Long couponId;
    @Column(name = "count", nullable = false)
    private int count;
    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
}
