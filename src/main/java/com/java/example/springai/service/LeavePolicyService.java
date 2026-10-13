package com.java.example.springai.service;

import org.springframework.stereotype.Service;

@Service
public class LeavePolicyService {

    public String getLeavePolicy() {
        return """
                Annual leave policy:
                - Employees receive 18 annual leave days per year.
                - Leave requests require sufficient available balance.
                - Weekends are not counted as working days.
                - Public holidays are excluded from working days.
                - Manager approval is required before leave is confirmed.
                """;
    }
}