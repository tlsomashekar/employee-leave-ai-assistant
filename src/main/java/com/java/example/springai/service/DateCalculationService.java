package com.java.example.springai.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class DateCalculationService {

    public String countWorkingDays(String startDate, String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        if (end.isBefore(start)) {
            return "Invalid date range: end date is before start date.";
        }

        long workingDays = start.datesUntil(end.plusDays(1))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .count();

        return "There are " + workingDays
                + " working days between " + startDate
                + " and " + endDate + ".";
    }
}
