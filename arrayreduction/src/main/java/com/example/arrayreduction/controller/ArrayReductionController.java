package com.example.arrayreduction.controller;

import com.example.arrayreduction.dto.ReductionResponse;
import com.example.arrayreduction.service.ArrayReductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/array")
@RequiredArgsConstructor
public class ArrayReductionController {

    private final ArrayReductionService arrayReductionService;

    @PostMapping("/zero-except-first")
    public ReductionResponse canMakeAllZeroExceptFirst(@RequestBody int[] array) {
        int result = arrayReductionService.canMakeAllZeroExceptFirst(array);
        return new ReductionResponse(result);
    }
}