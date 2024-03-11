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
            /*RegisterRequest c1 = new RegisterRequest("John", "Doe", "user@gmail.com", "password",Role.USER);
            RegisterRequest c2 = new RegisterRequest("Jane", "Smith", "admin@gmail.com", "password",Role.ADMIN);
            RegisterRequest c3 = new RegisterRequest("Alice", "Johnson", "alice@gmail.com", "password",Role.USER);
            RegisterRequest c4 = new RegisterRequest("Bob", "Brown", "bob@gmail.com", "password",Role.USER);
            AuthenticationService.register(c1);
            AuthenticationService.register(c2);
            AuthenticationService.register(c3);
            AuthenticationService.register(c4);*/
        };
    }
}