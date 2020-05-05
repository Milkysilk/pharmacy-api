package com.example.pharmacy.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/token";
//    八小时工作制（x）
//    便于debug（\/）
    public static final long TOKEN_VALIDITY_MILLISECONDS = 8 * 60 * 60 * 1000;
    public static final SignatureAlgorithm HS512 = SignatureAlgorithm.HS512;
//    用于debug
    public static final String SECRET_STRING = "vS8N/r67aj7cERAOlW6fZFKcdcJlYdN0o+Ja4C3y/G0kooOqmAe7iDb4cJtixQJ6bqBIdolc+6kebKGth4ZtHw==";
//    public static final String SECRET_STRING = Encoders.BASE64.encode(Keys.secretKeyFor(HS512).getEncoded());
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_ISSUER = "pharmacy-api";
    public static final String TOKEN_AUDIENCE = "pharmacy-app";
}
