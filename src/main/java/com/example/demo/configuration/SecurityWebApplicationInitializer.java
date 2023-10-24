package com.example.demo.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

@Component
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    // Наличие этого класса и наследование его от AbstractSecurityWebApplicationInitializer включает беопасность,
    // больше здесь ничего писать не надо
}
