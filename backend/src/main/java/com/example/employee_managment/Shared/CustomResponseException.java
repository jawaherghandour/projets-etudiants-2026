package com.example.employee_managment.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomResponseException extends RuntimeException {
    private int code;
    private String message;

    public static CustomResponseException ResourceNotFound(String message) {
        return new CustomResponseException(404, message);
    }
    public static CustomResponseException badCredentials() {
        return new CustomResponseException(401, "bad Credentials");
    }

    public static RuntimeException BadRequest(String message) {
        return new RuntimeException(message);
    }
}
