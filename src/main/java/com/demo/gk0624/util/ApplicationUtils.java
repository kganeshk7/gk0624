package com.demo.gk0624.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class ApplicationUtils {

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static boolean isHoliday(LocalDate date) {
        // Independence Day logic
        LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4); // 4th July, This can be configurable if we consider to have more holidays later in the future.
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1); // If Sturday then based on requirement Observe it on Friday
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1); // If Sunday then consider Monday as Holiday
        }
        // Labor Day logic
        LocalDate laborDay = LocalDate.of(date.getYear(), 9, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // First Monday

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}
