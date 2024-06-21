package com.stesla.love.admin.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private Map<String, Object> map = new HashMap<>();

    public Result(int code, String message, Object data) {
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
    }

    public Result() {
    }

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result success() {
        return new Result(200, "success", null);
    }

    public static Result error() {
        return new Result(400, "error", null);
    }

    public static Result error(int code,Object data) {
        return new Result(code, "error", data);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}