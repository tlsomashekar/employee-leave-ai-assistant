package com.java.example.springai.service;

public class EmployeeService {

       public String getEmployeeLeaveBalance(String employee) {

        // For now, pretend this came from a database.
        return switch (employee.toLowerCase()) {
            case "john" -> "John has 12 days of annual leave remaining.";
            case "mary" -> "Mary has 8 days of annual leave remaining.";
            case "alex" -> "Alex has 15 days of annual leave remaining.";
            default -> "Employee not found.";
        };
    }
}
