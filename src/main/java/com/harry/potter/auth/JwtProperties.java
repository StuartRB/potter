package com.harry.potter.auth;

import org.springframework.context.annotation.Configuration;

public class JwtProperties {
    public static final String SECRET = "IamtheWinner";
    public static final int EXPIRATION_TIME = 864000000; // 10 DAYS
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";
}
