package com.demo.gk0624.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalRequest {

    @NotEmpty(message = "toolCode must not be empty")
    private String toolCode;
    @Min( value=1, message = "rentalDays must be at least 1 day")
    private int rentalDays;

    @Min(value = 0, message = "Discount percent must be at least 0")
    @Max(value = 100, message = "Discount percent must be at most 100")
    private int discountPercent;

    @NotNull(message = "Checkout date can't be empty")
    private LocalDate checkoutDate;
}