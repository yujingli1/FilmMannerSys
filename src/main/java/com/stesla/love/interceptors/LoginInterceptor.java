package com.stesla.love.interceptors;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stesla.love.utils.JwtUtil;
import com.stesla.love.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        try {
            Map<String, Object> map = new HashMap<>();
            DecodedJWT decodedJWT = JwtUtil.getToken(authorization);
            Map<String, Claim> claims = decodedJWT.getClaims();
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                if (entry.getKey().equals("admin")) {
                    // fastjson json转map
                    String json = entry.getValue().asString();
                    Map<String, Object> map1 = JSONObject.parse(json);
                    map.put(entry.getKey(), map1);
                } else {
                    map.put(entry.getKey(), entry.getValue().asString());
                }
            }
            ThreadLocalUtil.set(map);
            response.setStatus(200);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
