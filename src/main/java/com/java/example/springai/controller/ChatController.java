package com.java.example.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.example.springai.model.LeaveAssessmentRequest;
import com.java.example.springai.model.LeaveRequest;
import com.java.example.springai.service.DateCalculationService;
import com.java.example.springai.service.EmployeeService;
import com.java.example.springai.service.LeavePolicyService;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final EmployeeService employeeService;
    private final LeavePolicyService leavePolicyService;
    private final DateCalculationService dateCalculationService;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.employeeService = new EmployeeService();
        this.leavePolicyService = new LeavePolicyService();
        this.dateCalculationService = new DateCalculationService();
    }

    @GetMapping("/ai")
    public String askAI(
            @RequestParam String message) {

        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/leave")
    public LeaveRequest createLeaveRequest(@RequestParam String message) {

        return chatClient
                .prompt()
                .system("""
                        Extract leave request information from the user's message.

                        Rules:
                        - Return only information that can be reasonably inferred.
                        - If employee is not mentioned, use "unknown".
                        - If leave type is not mentioned, infer it only when obvious.
                        - Use ISO date format: yyyy-MM-dd.
                        - Do not invent dates or information.
                        """)
                .user(message)
                .call()
                .entity(LeaveRequest.class);
    }

    @Tool(description = "Get the remaining annual leave balance for an employee")
    public String getLeaveBalance(String employee) {
        return employeeService.getEmployeeLeaveBalance(employee);
    }

    @GetMapping("/leave-balance")
    public String leaveBalance(@RequestParam String message) {

        return chatClient
                .prompt()
                .system("""
                        You are an HR assistant.

                        Help employees understand their leave balance.

                        If the user asks about a specific employee's
                        leave balance, use the getLeaveBalance tool.

                        Do not invent leave balances.
                        """)
                .user(message)
                .tools(this)
                .call()
                .content();
    }
    
    @Tool(description = "Get the annual leave policy and eligibility rules")
public String getLeavePolicy() {
    return leavePolicyService.getLeavePolicy();
}

@Tool(description = "Calculate the number of working days between two dates. Dates must use yyyy-MM-dd format.")
public String countWorkingDays(String startDate, String endDate) {
    return dateCalculationService.countWorkingDays(startDate, endDate);
}

@PostMapping("/leave-assessment")
public String assessLeave(@RequestBody LeaveAssessmentRequest request) {

    return chatClient
            .prompt()
            .system("""
                    You are an HR leave assessment assistant.

                    You have access to three tools:
                    1. getLeaveBalance: retrieves an employee's available leave.
                    2. getLeavePolicy: retrieves company leave rules.
                    3. countWorkingDays: calculates working days between dates.

                    Instructions:
                    - Use the tools to retrieve the required information.
                    - Use the employee name and dates from the request.
                    - Check the leave balance against the working days required.
                    - Explain your calculation.
                    - Do not invent employee balances or policy details.
                    - Do not claim leave has been approved or booked.
                    - If information is missing, ask for clarification.
                    """)
            .user(request.message())
            .tools(this)
            .call()
            .content();
}
}