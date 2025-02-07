package com.jaycode.demo.utils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean(name = "filterJwtFilter")
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(new JwtUtil()));  // Pass JwtUtil to the filter
        registrationBean.addUrlPatterns("/api/v1/**"); // Apply filter to specific URLs that require authentication
        return registrationBean;
    }
}
