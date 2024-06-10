package com.demo.gk0624.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Table(name = "TOOL_INFO")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolInfo {
    @Id
    @Column(name = "tool_code ")
    private String toolCode;
    @Column(name = "tool_type  ")
    private String toolType;
    @Column(name = "brand")
    private String brand;
    @Column(name = "daily_charge")
    private BigDecimal dailyCharge;
    @Column(name = "weekday_charge")
    private boolean weekdayCharge;
    @Column(name = "weekend_charge")
    private boolean weekendCharge;
    @Column(name = "holiday_charge")
    private boolean holidayCharge;
}