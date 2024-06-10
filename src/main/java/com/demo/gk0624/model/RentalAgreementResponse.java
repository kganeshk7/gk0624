package com.demo.gk0624.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RentalAgreementResponse {
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

    public void print() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Tool brand: " + brand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.printf("Daily rental charge: $%.2f%n", dailyCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.printf("Pre-discount charge: $%.2f%n", preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.printf("Discount amount: $%.2f%n", discountAmount);
        System.out.printf("Final charge: $%.2f%n", finalCharge);
    }
}
