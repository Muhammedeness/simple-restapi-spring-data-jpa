package com.enesselvi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/enes/rest/api/**") // Hangi API yolları için geçerli olacak
                .allowedOrigins("http://localhost:5500") // PHP'deki 'Access-Control-Allow-Origin: *' karşılığı
                .allowedMethods("GET", "HEAD", "OPTIONS", "POST", "PUT") // PHP'deki 'Access-Control-Allow-Methods' karşılığı
                .allowedHeaders("Access-Control-Allow-Headers", "Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers") // PHP'deki 'Access-Control-Allow-Headers' karşılığı
                .allowCredentials(true) // PHP'deki 'Access-Control-Allow-Credentials: TRUE' karşılığı
                .maxAge(3600); // Ön kontrol (preflight) isteğinin ne kadar süreyle cache'leneceği (saniye)
    }
}