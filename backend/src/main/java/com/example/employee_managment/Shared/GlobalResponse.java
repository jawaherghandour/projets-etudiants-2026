package com.example.employee_managment.Shared;

import lombok.Getter;

import java.util.List;

@Getter
public class GlobalResponse<T> {
    public final static String Success = "success";
    public final static String Errors = "errors";
    private final String status;
    private final T data;
    private final List<ErrorItem> errors;

    public GlobalResponse(List<ErrorItem> errors) {
        this.status = Errors;
        this.data = null;
        this.errors = errors;
    }

    public GlobalResponse(T data) {
        this.status = Success;
        this.data = data;
        this.errors = null;
    }

    public record ErrorItem(String message) {

    }
}
