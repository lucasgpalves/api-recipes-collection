package com.college.recipes_collection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http 
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests( authorize -> authorize
                /* BOOK */
                .requestMatchers(HttpMethod.POST, "/books").hasRole("EDITOR")
                .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/books/**").hasRole("EDITOR")
            
                /* CATEGORY */
                .requestMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                /* GOAL */
                .requestMatchers(HttpMethod.POST, "/goals").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/goals/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/goals/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/goals/**").hasRole("ADMIN")

                /* INGREDIENT */
                .requestMatchers(HttpMethod.POST, "/ingredients").hasRole("COOK")
                .requestMatchers(HttpMethod.GET, "/ingredients/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/ingredients/**").hasRole("COOK")
                .requestMatchers(HttpMethod.DELETE, "/ingredients/**").hasRole("COOK")

                /* MEASUREMENT */
                .requestMatchers(HttpMethod.POST, "/measurement").hasRole("COOK")
                .requestMatchers(HttpMethod.GET, "/measurement/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/measurement/**").hasRole("COOK")
                .requestMatchers(HttpMethod.DELETE, "/measurement/**").hasRole("COOK")

                /* RECIPE */
                .requestMatchers(HttpMethod.POST, "/recipes").hasRole("COOK")
                .requestMatchers(HttpMethod.GET, "/recipes/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/recipes/**").hasRole("COOK")

                /* REVIEW */
                .requestMatchers(HttpMethod.POST, "/reviews").hasRole("TASTER")
                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/reviews/**").hasRole("TASTER")
                .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasRole("TASTER")

                /* ROLE */
                .requestMatchers(HttpMethod.POST, "/roles").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/roles/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/roles/**").hasRole("ADMIN")

                /* USER */
                .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")     
                
                .anyRequest().authenticated()
            )
            .build();
    }
}
