package com.banking.notificationservice.controller;

import com.banking.notificationservice.dto.NotificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendNotification(
            @RequestBody NotificationRequest request) {

        System.out.println("=================================");
        System.out.println("NOTIFICATION ALERT!");
        System.out.println("Account  : " + request.getAccountNumber());
        System.out.println("Type     : " + request.getType());
        System.out.println("Amount   : " + request.getAmount());
        System.out.println("Message  : " + request.getMessage());
        System.out.println("Time     : " + LocalDateTime.now());
        System.out.println("=================================");

        Map<String, String> response = new HashMap<>();
        response.put("status", "Notification sent successfully!");
        response.put("account", request.getAccountNumber());
        response.put("type", request.getType());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Notification Service is running!");
    }
}