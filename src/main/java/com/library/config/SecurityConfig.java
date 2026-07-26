package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders
        .HttpSecurity;

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


                // PUBLIC
                .requestMatchers(

                        "/",

                        "/login-page",

                        "/register-page",

                        "/auth/**",

                        "/css/**",

                        "/js/**",

                        "/images/**",

                        "/favicon.ico",

                        "/swagger-ui/**",

                        "/swagger-ui.html",

                        "/v3/api-docs/**"

                )

                .permitAll()


                // HTML pages
                .requestMatchers(

                        "/dashboard-page",

                        "/manage-books",

                        "/manage-students",

                        "/issue-page",

                        "/return-page"

                )

                .permitAll()


                // BOOK APIs
                .requestMatchers(
                        "/books/**")

                .hasRole("ADMIN")


                // STUDENT APIs
                .requestMatchers(
                        "/students/**")

                .hasAnyRole(
                        "ADMIN",
                        "STUDENT")


                // ISSUE/RETURN APIs
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