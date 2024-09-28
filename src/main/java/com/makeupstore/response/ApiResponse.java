package com.makeupstore.response;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    Map<String, Object> response;

    public ApiResponse(String message, Object status, Object data) {
        response = new HashMap<>();

        response.put("message", message);
        response.put("status", status);
        response.put("data", data);
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
