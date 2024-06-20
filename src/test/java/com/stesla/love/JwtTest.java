package com.stesla.love;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void test() {
        String token = JWT.create()
                .withClaim("name", 1)
                .withClaim("age", 18)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24))
                .sign(Algorithm.HMAC384("123456"));
    }
}
