package com.example.Coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserCouponRedemption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id;
    private Long coupon_id;
    private Long product_id;
    private LocalDateTime give_date;
    private LocalDateTime used_date;
    private LocalDateTime expire_date;
    private int discount_type;
    private double discount_amount;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
}
