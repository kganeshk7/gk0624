package com.demo.gk0624.service;

import com.demo.gk0624.entity.RentalAgreement;
import com.demo.gk0624.entity.Tool;
import com.demo.gk0624.repo.RentalAgreementRepository;
import com.demo.gk0624.repo.ToolRepository;
import com.demo.gk0624.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ToolRentalService {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be at least 1");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }

        Tool tool = toolRepository.findById(toolCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tool code"));

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(tool, checkoutDate, dueDate);

        BigDecimal preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100));
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setToolCode(toolCode);
        rentalAgreement.setToolType(tool.getToolType());
        rentalAgreement.setBrand(tool.getBrand());
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(dueDate);
        rentalAgreement.setDailyCharge(tool.getDailyCharge());
        rentalAgreement.setChargeDays(chargeDays);
        rentalAgreement.setPreDiscountCharge(preDiscountCharge);
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(discountAmount);
        rentalAgreement.setFinalCharge(finalCharge);

        rentalAgreementRepository.save(rentalAgreement);
        return rentalAgreement;
    }

    private int calculateChargeDays(Tool tool, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1);

        while (!currentDate.isAfter(dueDate)) {
            boolean isWeekend = ApplicationUtils.isWeekend(currentDate);
            boolean isHoliday = ApplicationUtils.isHoliday(currentDate);

            if (tool.isWeekdayCharge() && !isWeekend && !isHoliday) {
                chargeDays++;
            } else if (tool.isWeekendCharge() && isWeekend && !isHoliday) {
                chargeDays++;
            } else if (tool.isHolidayCharge() && isHoliday) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return chargeDays;
    }
}