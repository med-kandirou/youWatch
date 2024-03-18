package com.medkandirou.youwatch.channel;

import com.medkandirou.youwatch.security.auth.AuthenticationService;
import com.medkandirou.youwatch.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Order(2)
public class ChannelConfig {
    @Bean
    CommandLineRunner commandLineRunnerChannel(AuthenticationService AuthenticationService) {
        return args -> {

        };
    }
}