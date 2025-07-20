package com.example.closestminimums.controller;

import com.example.closestminimums.dto.ClosestMinimumsResponse;
import com.example.closestminimums.service.ClosestMinimumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/minimums")
@RequiredArgsConstructor
public class ClosestMinimumsController {

    private final ClosestMinimumsService closestMinimumsService;

    @PostMapping("/distance")
    public ClosestMinimumsResponse findClosestMinimumsDistance(@RequestBody int[] numbers) {
        Integer result = closestMinimumsService.findClosestMinimumsDistance(numbers);
        return new ClosestMinimumsResponse(result);
    }
}