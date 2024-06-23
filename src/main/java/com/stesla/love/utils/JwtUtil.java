package com.stesla.love.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "stesla";
    private static final long EXPIRATION = 604800000L;

    /**
     * 生成token
     *
     * @param map //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String, Object> map) {

        JWTCreator.Builder builder = JWT.create();

        map.forEach((k, v) -> {
            if (v instanceof String) {
                builder.withClaim(k, (String) v);
            } else if (v instanceof Integer) {
                builder.withClaim(k, (Integer) v);
            } else if (v instanceof Long) {
                builder.withClaim(k, (Long) v);
            } else if (v instanceof Double) {
                builder.withClaim(k, (Double) v);
            } else if (v instanceof Date) {
                builder.withClaim(k, (Date) v);
            } else {
                builder.withClaim(k, v.toString());
            }
        });

        return builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 验证token
     *
     * @param token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
    }

    /**
     * 获取token中payload
     *
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
    }

}

