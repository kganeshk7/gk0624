package com.demo.gk0624.service;

import com.demo.gk0624.exception.ApplicationException;
import com.demo.gk0624.model.RentalAgreementResponse;
import com.demo.gk0624.entity.ToolInfo;
import com.demo.gk0624.repo.ToolRepository;
import com.demo.gk0624.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class ToolRentalService {

    @Autowired
    private ToolRepository toolRepository;

    /**
     * Method to calculate charges on tool. This calcualte charge days and final price based on holiday
     * @param toolCode - Actual Tool Code
     * @param rentalDays - No of rental days for the tool
     * @param discountPercent - discount percentage if applicable
     * @param checkoutDate - Actual date when checking out the tool from the store
     * @Exception -
     *   - Rental days must be >1
     *   - toolCode must be present in dataase.
     * @return RentalAgreementResponse tool charges with all the details including no of charge days
     */
    public RentalAgreementResponse checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new ApplicationException("Rental day count must be at least 1");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new ApplicationException("Discount percent must be between 0 and 100");
        }

        ToolInfo tool = toolRepository.findById(toolCode)
                .orElseThrow(() -> new ApplicationException("Invalid tool code"));

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(tool, checkoutDate, dueDate);

        BigDecimal preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100));
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        RentalAgreementResponse rentalAgreementResponse = new RentalAgreementResponse();
        rentalAgreementResponse.setToolCode(toolCode);
        rentalAgreementResponse.setToolType(tool.getToolType());
        rentalAgreementResponse.setBrand(tool.getBrand());
        rentalAgreementResponse.setRentalDays(rentalDays);
        rentalAgreementResponse.setCheckoutDate(checkoutDate);
        rentalAgreementResponse.setDueDate(dueDate);
        rentalAgreementResponse.setDailyCharge(tool.getDailyCharge());
        rentalAgreementResponse.setChargeDays(chargeDays);
        rentalAgreementResponse.setPreDiscountCharge(preDiscountCharge.setScale(2, RoundingMode.HALF_UP));
        rentalAgreementResponse.setDiscountPercent(discountPercent);
        rentalAgreementResponse.setDiscountAmount(discountAmount.setScale(2, RoundingMode.HALF_UP));
        rentalAgreementResponse.setFinalCharge(finalCharge.setScale(2, RoundingMode.HALF_UP)); // rounding this due to test case failure.. Though it is not mentioned in the document to round final charges.
        rentalAgreementResponse.print(); // Printing to console as requested in doc
        return rentalAgreementResponse;
    }

    /**
     * Calculate charge days between checkout and dueDate. Method also considers if there
     * is any weekend or weekdays holidays such as 4th July or Labor days.
     * @param tool - Tool Code
     * @param checkoutDate chekout Date fo the tool
     * @param dueDate - Due Date when tool will be returned.
     * @return - No of charge days between checkout and dueDate start and endDate inclusive
     */
    private int calculateChargeDays(ToolInfo tool, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate; // Including both start date and end date as part of charges 

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