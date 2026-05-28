package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication
.UsernamePasswordAuthenticationFilter;

import com.library.filter.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(
            JwtFilter jwtFilter){

        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain
    securityFilterChain(
            HttpSecurity http)

            throws Exception {

        http

        .csrf(csrf ->
                csrf.disable())

        .authorizeHttpRequests(auth ->

            auth

            // Public pages + APIs

            .requestMatchers(

                    "/",

                    "/login-page",

                    "/register-page",

                    "/auth/**",

                    "/css/**",

                    "/js/**",

                    "/images/**",

                    "/swagger-ui/**",

                    "/swagger-ui.html",

                    "/v3/api-docs/**"

            )

            .permitAll()


            // UI pages after login

            .requestMatchers(

                    "/dashboard-page",

                    "/manage-books",

                    "/manage-students",

                    "/issue-page",

                    "/return-page"

            )

            .permitAll()

            // API rules

            .requestMatchers(
                    "/books/**")

            .hasRole("ADMIN")


            .requestMatchers(
                    "/students/**")

            .hasAnyRole(
                    "ADMIN",
                    "STUDENT")


            .requestMatchers(
                    "/issue/**")

            .hasRole("ADMIN")


            .anyRequest()

            .authenticated()
        )

        .formLogin(
                form ->
                form.disable());


        http.addFilterBefore(

                jwtFilter,

                UsernamePasswordAuthenticationFilter.class

        );

        return http.build();

    }

}