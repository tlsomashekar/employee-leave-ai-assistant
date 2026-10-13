package com.java.example.springai;

import org.springframework.stereotype.Component;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;

@Component
public class LeaveMcpTools {

    @McpTool(
            name = "get_leave_balance",
            description = "Get the remaining annual leave balance for an employee"
    )
    public String getLeaveBalance(
            @McpToolParam(
                    description = "Employee name, for example John or Mary",
                    required = true
            )
            String employeeName
    ) {
        return switch (employeeName.trim().toLowerCase()) {
            case "john" -> "John has 12 leave days remaining.";
            case "mary" -> "Mary has 8 leave days remaining.";
            case "alex" -> "Alex has 15 leave days remaining.";
            default -> "No leave balance found for "
                    + employeeName + ".";
        };
    }
}
