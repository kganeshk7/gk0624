package com.demo.gk0624.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name="RENTAL_AGGREEMENT")
@Entity
@Data
public class RentalAgreement {
    // rental aggreement is supposed to have a relation with User / Driver's license but since requirment is not ,I am not considering the relation for now.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String toolCode;
    private String toolType;
    private String brand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

}
