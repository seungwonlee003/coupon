package com.example.coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long user_id;
    @NotNull
    private Long coupon_id;
    private Long product_id;
    @NotNull
    private LocalDateTime give_date;
    private LocalDateTime used_date;
    @NotNull
    private LocalDateTime expire_date;
    @NotNull
    private int discount_type;
    @NotNull
    private double discount_amount;
    @NotNull
    private LocalDateTime created_at;
    @NotNull
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
}
