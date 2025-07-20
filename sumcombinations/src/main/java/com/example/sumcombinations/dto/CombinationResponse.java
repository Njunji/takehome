package com.example.sumcombinations.dto;

import java.util.List;

public record CombinationResponse(
        int count,
        String message,
        List<List<Integer>> combinations
) {
}