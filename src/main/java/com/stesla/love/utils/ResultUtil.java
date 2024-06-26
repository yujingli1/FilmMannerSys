package com.stesla.love.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
    private Map<String, Object> map = new HashMap<>();

    public ResultUtil(int code, String message, Object data) {
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
    }

    public ResultUtil() {
    }

    public static ResultUtil success(Object data) {
        return new ResultUtil(200, "success", data);
    }

    public static ResultUtil success() {
        return new ResultUtil(200, "success", null);
    }

    public static ResultUtil error() {
        return new ResultUtil(400, "error", null);
    }

    public static ResultUtil error(int code, Object data) {
        return new ResultUtil(code, "error", data);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}