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
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CouponStock couponStock;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "expire_minute", nullable = false)
    private int expireMinute;

    @Column(name = "discount_type", nullable = false)
    private int discountType;

    @Column(name = "discount_amount", nullable = false)
    private double discountAmount;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;
}
