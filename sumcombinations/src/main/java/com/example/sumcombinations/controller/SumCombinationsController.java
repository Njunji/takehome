package com.example.sumcombinations.controller;

import com.example.sumcombinations.dto.CombinationResponse;
import com.example.sumcombinations.service.SumCombinationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumCombinationsController {

    private final SumCombinationsService service;

    @Autowired
    public SumCombinationsController(SumCombinationsService service) {
        this.service = service;
    }

    public record CombinationRequest(int result, int[] numbers) {
    }

    @PostMapping("/find-combinations")
    public ResponseEntity<CombinationResponse> findCombinations(@RequestBody CombinationRequest request) {
        CombinationResponse response = service.findSumCombinations(request.result(), request.numbers());
        return ResponseEntity.ok(response);
    }
}