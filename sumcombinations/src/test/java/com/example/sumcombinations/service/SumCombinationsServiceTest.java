package com.example.sumcombinations.service;

import com.example.sumcombinations.dto.CombinationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SumCombinationsServiceTest {

    @InjectMocks
    private SumCombinationsService service;

    @Test
    void testExample1() {
        int result = 10;
        int[] numbers = {1, 2, 3, 4, 5};
        CombinationResponse response = service.findSumCombinations(result, numbers);

        assertEquals(3, response.count());
        assertEquals("Found 3 combinations", response.message());
        List<List<Integer>> combinations = response.combinations();
        assertEquals(3, combinations.size());
        assertTrue(combinations.contains(List.of(1, 2, 3, 4)));
        assertTrue(combinations.contains(List.of(1, 4, 5)));
        assertTrue(combinations.contains(List.of(2, 3, 5)));
    }

    @Test
    void testExample2() {
        int result = 17;
        int[] numbers = {1, 2, 4, 7, 5};
        CombinationResponse response = service.findSumCombinations(result, numbers);

        assertEquals(1, response.count());
        assertEquals("Found 1 combinations", response.message());
        List<List<Integer>> combinations = response.combinations();
        assertEquals(1, combinations.size());
        assertTrue(combinations.contains(List.of(1, 4, 5, 7)), "Expected combination [1, 4, 5, 7] not found");
    }

    @Test
    void testNoCombinations() {
        int result = 20;
        int[] numbers = {1, 2, 3};
        CombinationResponse response = service.findSumCombinations(result, numbers);

        assertEquals(0, response.count());
        assertEquals("No combinations found", response.message());
        assertTrue(response.combinations().isEmpty());
    }

    @Test
    void testNullOrEmptyInput() {
        CombinationResponse response1 = service.findSumCombinations(10, null);
        assertEquals(0, response1.count());
        assertEquals("No combinations: invalid input (null or empty array)", response1.message());
        assertTrue(response1.combinations().isEmpty());

        CombinationResponse response2 = service.findSumCombinations(10, new int[]{});
        assertEquals(0, response2.count());
        assertEquals("No combinations: invalid input (null or empty array)", response2.message());
        assertTrue(response2.combinations().isEmpty());
    }

    @Test
    void testSingleNumberEqualsResult() {
        int result = 5;
        int[] numbers = {5};
        CombinationResponse response = service.findSumCombinations(result, numbers);

        assertEquals(1, response.count());
        assertEquals("Found 1 combinations", response.message());
        List<List<Integer>> combinations = response.combinations();
        assertEquals(1, combinations.size());
        assertTrue(combinations.contains(List.of(5)));
    }
}