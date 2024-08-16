package com.aalok.WalletService.WalletService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletConfig {
    @Bean
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }
}
