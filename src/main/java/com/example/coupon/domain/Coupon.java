package com.example.coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int type;
    private int count;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int expire_minute;
    private int discount_type;
    private double discount_amount;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

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
