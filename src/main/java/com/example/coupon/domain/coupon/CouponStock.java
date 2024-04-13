package com.example.coupon.domain.coupon;

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
public class CouponStock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "id", nullable = false)
    private Coupon coupon;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    public CouponStock(Coupon coupon, int count) {
        this.coupon = coupon;
        this.count = count;
    }
}
