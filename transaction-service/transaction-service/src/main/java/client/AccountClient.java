package com.banking.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/api/accounts/{accountNumber}")
    Map<String, Object> getAccount(@PathVariable String accountNumber);

    @PutMapping("/api/accounts/{accountNumber}/balance")
    Map<String, Object> updateBalance(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount
    );
}