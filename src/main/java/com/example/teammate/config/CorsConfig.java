package com.example.teammate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // 허용되는 http url
        config.addAllowedHeader("*"); // 사전에 허용되는 헤더
        config.addAllowedMethod("*"); // 사용가능한 메서드 목록

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
