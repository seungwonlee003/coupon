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
public class Coupon extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public static boolean validateType(int type, int count){
        return type == 0 && count == 0 || type == 1 && count > 0;
    }

    public static boolean validateDiscountType(int discount_type, double discount_amount){
        return discount_type == 0 && discount_amount > 0
                || (discount_type == 1 && discount_amount > 0 && discount_amount <= 100);
    }

    public static boolean validateDate(LocalDateTime startDate, LocalDateTime endDate){
        return startDate.isAfter(LocalDateTime.now()) && startDate.isBefore(endDate);
    }

    public static boolean validateExpireMinute(int expireMinute){
        return expireMinute > 0;
    }
}
