package com.java.example.springai.model;

public record LeaveRequest(   String employee,
        String leaveType,
        String startDate,
        String endDate,
        String reason) {
    
    
}
