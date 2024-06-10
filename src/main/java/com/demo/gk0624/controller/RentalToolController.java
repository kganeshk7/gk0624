package com.demo.gk0624.controller;

import com.demo.gk0624.model.RentalAgreementResponse;
import com.demo.gk0624.model.RentalRequest;
import com.demo.gk0624.service.ToolRentalService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/rentals")
@Slf4j
public class RentalToolController {
        @Autowired
        private ToolRentalService toolRentalService;

        @PostMapping("/checkout")
        public ResponseEntity<RentalAgreementResponse> checkout(@Valid @RequestBody RentalRequest rentalRequest) {
            log.info("request received");
            RentalAgreementResponse rentalAgreementResponse = toolRentalService.checkout(
                    rentalRequest.getToolCode(),
                    rentalRequest.getRentalDays(),
                    rentalRequest.getDiscountPercent(),
                    rentalRequest.getCheckoutDate()
            );
            return ResponseEntity.ok(rentalAgreementResponse);
        }
}
