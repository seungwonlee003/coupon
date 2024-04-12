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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // thought about using foreign key relationship instead of relying on ORM
    // though, whenever a list of coupons is retrieved, coupon stocks should also be retrieved
    // for updating coupon count which expects high concurrency, only coupon stock entity
    // can be fetched with a JPQL query.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id", nullable = false)
    private Coupon coupon;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
}
