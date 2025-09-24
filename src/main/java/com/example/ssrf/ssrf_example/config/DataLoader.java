package com.example.ssrf.ssrf_example.config;

import com.example.ssrf.ssrf_example.repository.UserRepository;
import com.example.ssrf.ssrf_example.model.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository repo) {
        return args -> {
            repo.save(new UserEntity("Alice", "alice@example.com"));
            repo.save(new UserEntity("Bob", "bob@example.com"));
            repo.save(new UserEntity("Carol", "carol@example.com"));
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Orígenes específicos (más seguro para producción)
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:8080",
                "http://localhost:8081",
                "https://ssrfexample-azerf2gthpa2hegv.canadacentral-01.azurewebsites.net"
        ));

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD"
        ));

        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Permitir credenciales (cookies, headers de autorización)
        configuration.setAllowCredentials(true);


        // Tiempo de cache para preflight requests
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}