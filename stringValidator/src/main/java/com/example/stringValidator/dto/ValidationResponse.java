package com.example.stringValidator.dto;

import java.util.List;

public record ValidationResponse(
        String longestValidString,
        String message,
        List<StringValidationResult> results
) {
    public record StringValidationResult(String input, boolean isValid, String reason) {
    }
}