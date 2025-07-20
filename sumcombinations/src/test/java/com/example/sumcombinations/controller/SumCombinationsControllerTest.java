package com.example.sumcombinations.controller;

import com.example.sumcombinations.dto.CombinationResponse;
import com.example.sumcombinations.service.SumCombinationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SumCombinationsControllerTest {

    @Mock
    private SumCombinationsService service;

    @InjectMocks
    private SumCombinationsController controller;

    @Test
    void testFindCombinationsExample1() {
        int result = 10;
        int[] numbers = {1, 2, 3, 4, 5};
        CombinationResponse response = new CombinationResponse(
                3,
                "Found 3 combinations",
                List.of(
                        List.of(1, 2, 3, 4),
                        List.of(1, 4, 5),
                        List.of(2, 3, 5)
                )
        );

        when(service.findSumCombinations(result, numbers)).thenReturn(response);

        SumCombinationsController.CombinationRequest request = new SumCombinationsController.CombinationRequest(result, numbers);
        ResponseEntity<CombinationResponse> resultResponse = controller.findCombinations(request);

        assertEquals(3, resultResponse.getBody().count());
        assertEquals("Found 3 combinations", resultResponse.getBody().message());
        List<List<Integer>> combinations = resultResponse.getBody().combinations();
        assertEquals(3, combinations.size());
        assertTrue(combinations.contains(List.of(1, 2, 3, 4)));
        assertTrue(combinations.contains(List.of(1, 4, 5)));
        assertTrue(combinations.contains(List.of(2, 3, 5)));
    }

    @Test
    void testNoCombinations() {
        int result = 20;
        int[] numbers = {1, 2, 3};
        CombinationResponse response = new CombinationResponse(0, "No combinations found", List.of());

        when(service.findSumCombinations(result, numbers)).thenReturn(response);

        SumCombinationsController.CombinationRequest request = new SumCombinationsController.CombinationRequest(result, numbers);
        ResponseEntity<CombinationResponse> resultResponse = controller.findCombinations(request);

        assertEquals(0, resultResponse.getBody().count());
        assertEquals("No combinations found", resultResponse.getBody().message());
        assertTrue(resultResponse.getBody().combinations().isEmpty());
    }
}