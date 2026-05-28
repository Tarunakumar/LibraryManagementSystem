package com.library.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static final String SECRET=

        "mysecretkeymysecretkeymysecretkey12345";



    public static String generateToken(
            String username){

        Key key = new SecretKeySpec(
                SECRET.getBytes(),
                SignatureAlgorithm
                .HS256
                .getJcaName());


        return Jwts.builder()

                .subject(username)

                .issuedAt(
                        new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                +1000*60*60))

                .signWith(key)

                .compact();
    }
    public static String extractUsername(
            String token){

        Key key = new SecretKeySpec(
                SECRET.getBytes(),
                SignatureAlgorithm
                .HS256.getJcaName());

        return Jwts.parser()

                .verifyWith(
                        (javax.crypto.SecretKey) key)

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();
    }
}