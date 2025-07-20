package com.example.stringValidator.controller;

import com.example.stringValidator.dto.ValidationResponse;
import com.example.stringValidator.service.StringValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StringValidatorController {

    private final StringValidatorService validatorService;

    public record ValidationRequest(String validCharacters, String[] strings) {
    }

    @PostMapping("/validate-longest")
    public ResponseEntity<ValidationResponse> findLongestValidString(@RequestBody ValidationRequest request) {
        ValidationResponse response = validatorService.findLongestValidString(request.validCharacters(), request.strings());
        return ResponseEntity.ok(response);
    }
}
