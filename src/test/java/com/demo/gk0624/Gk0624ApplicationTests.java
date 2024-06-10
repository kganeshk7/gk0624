package com.demo.gk0624;

import com.demo.gk0624.exception.ApplicationException;
import com.demo.gk0624.model.RentalAgreementResponse;
import com.demo.gk0624.model.RentalRequest;
import com.demo.gk0624.service.ToolRentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Gk0624ApplicationTests {

	@Autowired
	private ToolRentalService toolRentalService;

	// Important Note: Below repitative methods can be removed if we use TestNg and add these rates into excel file and that will save a lot of lines of code. Then we are going to have just 1 method
	// can validate everything But consider this is just a unit test case , I am repeating the method multiple times. Which I don't like :) .
	@Test
	void test1() {
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setToolCode("JAKR");
		rentalRequest.setRentalDays(5);
		rentalRequest.setDiscountPercent(101);
		rentalRequest.setCheckoutDate(LocalDate.of(2015, 9, 3));

		assertThrows(ApplicationException.class, () -> {
			toolRentalService.checkout(rentalRequest.getToolCode(), rentalRequest.getRentalDays(), rentalRequest.getDiscountPercent(), rentalRequest.getCheckoutDate());
		});
	}

	@Test
	void test2() {
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setToolCode("LADW");
		rentalRequest.setRentalDays(3);
		rentalRequest.setDiscountPercent(10);
		rentalRequest.setCheckoutDate(LocalDate.of(2020, 7, 2));

		RentalAgreementResponse rentalAgreement = toolRentalService.checkout(rentalRequest.getToolCode(), rentalRequest.getRentalDays(), rentalRequest.getDiscountPercent(), rentalRequest.getCheckoutDate());

		assertNotNull(rentalAgreement);
		assertEquals("LADW", rentalAgreement.getToolCode());
		assertEquals("Ladder", rentalAgreement.getToolType());
		assertEquals("Werner", rentalAgreement.getBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 7, 5), rentalAgreement.getDueDate());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("5.97"), rentalAgreement.getPreDiscountCharge());
		assertEquals(10, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.60"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("5.37"), rentalAgreement.getFinalCharge());
	}

	@Test
	void test3() {
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setToolCode("CHNS");
		rentalRequest.setRentalDays(5);
		rentalRequest.setDiscountPercent(25);
		rentalRequest.setCheckoutDate(LocalDate.of(2015, 7, 2));

		RentalAgreementResponse rentalAgreement = toolRentalService.checkout(rentalRequest.getToolCode(), rentalRequest.getRentalDays(), rentalRequest.getDiscountPercent(), rentalRequest.getCheckoutDate());

		assertNotNull(rentalAgreement);
		assertEquals("CHNS", rentalAgreement.getToolCode());
		assertEquals("Chainsaw", rentalAgreement.getToolType());
		assertEquals("Stihl", rentalAgreement.getBrand());
		assertEquals(5, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 7, 7), rentalAgreement.getDueDate());
		assertEquals(4, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("5.96"), rentalAgreement.getPreDiscountCharge());
		assertEquals(25, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("1.49"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("4.47"), rentalAgreement.getFinalCharge());
	}

	@Test
	void test4() {
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setToolCode("JAKD");
		rentalRequest.setRentalDays(6);
		rentalRequest.setDiscountPercent(0);
		rentalRequest.setCheckoutDate(LocalDate.of(2015, 9, 3));

		RentalAgreementResponse rentalAgreement = toolRentalService.checkout(rentalRequest.getToolCode(), rentalRequest.getRentalDays(), rentalRequest.getDiscountPercent(), rentalRequest.getCheckoutDate());

		assertNotNull(rentalAgreement);
		assertEquals("JAKD", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("DeWalt", rentalAgreement.getBrand());
		assertEquals(6, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 9, 3), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 9, 9), rentalAgreement.getDueDate());
		assertEquals(4, rentalAgreement.getChargeDays());  // Assuming weekends and holidays are no-charge for jackhammers  //
		assertEquals(new BigDecimal("11.96"), rentalAgreement.getPreDiscountCharge());
		assertEquals(0, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("11.96"), rentalAgreement.getFinalCharge());
	}

	@Test
	void test5() {
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setToolCode("JAKR");
		rentalRequest.setRentalDays(9);
		rentalRequest.setDiscountPercent(0);
		rentalRequest.setCheckoutDate(LocalDate.of(2015, 7, 2));

		RentalAgreementResponse  rentalAgreement = toolRentalService.checkout(rentalRequest.getToolCode(), rentalRequest.getRentalDays(), rentalRequest.getDiscountPercent(), rentalRequest.getCheckoutDate());

		assertNotNull(rentalAgreement);
		assertEquals("JAKR", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("Ridgid", rentalAgreement.getBrand());
		assertEquals(9, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 7, 11), rentalAgreement.getDueDate());
		assertEquals(6, rentalAgreement.getChargeDays());  // Assuming weekends and holidays are no-charge for jackhammers
		assertEquals(new BigDecimal("17.94"), rentalAgreement.getPreDiscountCharge());
		assertEquals(0, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("17.94"), rentalAgreement.getFinalCharge());
	}

	@Test
	void test6() {
		RentalRequest rentalRequest = new RentalRequest();
		rentalRequest.setToolCode("JAKR");
		rentalRequest.setRentalDays(4);
		rentalRequest.setDiscountPercent(50);
		rentalRequest.setCheckoutDate(LocalDate.of(2020, 7, 2));

		RentalAgreementResponse rentalAgreement = toolRentalService.checkout(rentalRequest.getToolCode(), rentalRequest.getRentalDays(), rentalRequest.getDiscountPercent(), rentalRequest.getCheckoutDate());

		assertNotNull(rentalAgreement);
		assertEquals("JAKR", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("Ridgid", rentalAgreement.getBrand());
		assertEquals(4, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 7, 6), rentalAgreement.getDueDate());
		assertEquals(2, rentalAgreement.getChargeDays());  // Assuming weekends and holidays are no-charge for jackhammers
		assertEquals(new BigDecimal("5.98"), rentalAgreement.getPreDiscountCharge());
		assertEquals(50, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("2.99"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("2.99"), rentalAgreement.getFinalCharge());
	}

}
