package com.library.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication
        .UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority
        .SimpleGrantedAuthority;

import org.springframework.security.core.context
        .SecurityContextHolder;

import org.springframework.stereotype.Component;

import org.springframework.web.filter
        .OncePerRequestFilter;

import com.library.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request) {

        String path =
                request.getServletPath();

        return
                path.equals("/") ||

                path.equals("/login-page") ||

                path.equals("/register-page") ||

                path.startsWith("/auth/") ||

                path.startsWith("/css/") ||

                path.startsWith("/js/") ||

                path.startsWith("/images/") ||

                path.startsWith("/swagger-ui") ||

                path.startsWith("/v3/api-docs") ||

                path.equals("/favicon.ico");
    }


    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException,
            IOException {


        String authHeader =
                request.getHeader(
                        "Authorization");


        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(
                    request,
                    response);

            return;
        }


        try{

            String token =
                    authHeader.substring(7);


            String username =
                    JwtUtil.extractUsername(
                            token);


            UsernamePasswordAuthenticationToken
                    authentication =

                    new UsernamePasswordAuthenticationToken(

                            username,

                            null,

                            List.of(
                                    new SimpleGrantedAuthority(
                                            "ROLE_ADMIN")
                            )

                    );


            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            authentication);


        }
        catch(Exception e){

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }


        filterChain.doFilter(
                request,
                response);
    }
}