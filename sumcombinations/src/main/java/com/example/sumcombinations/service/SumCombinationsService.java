package com.example.sumcombinations.service;

import com.example.sumcombinations.dto.CombinationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SumCombinationsService {

    public CombinationResponse findSumCombinations(int result, int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return new CombinationResponse(0, "No combinations: invalid input (null or empty array)", List.of());
        }

        // Sorts the array to ensure consistent combination order
        int[] sortedNumbers = numbers.clone();
        Arrays.sort(sortedNumbers);

        List<List<Integer>> combinations = new ArrayList<>();
        findCombinations(result, sortedNumbers, 0, new ArrayList<>(), combinations);

        String message = combinations.isEmpty() ? "No combinations found" : "Found " + combinations.size() + " combinations";
        return new CombinationResponse(combinations.size(), message, combinations);
    }

    private void findCombinations(int target, int[] numbers, int start, List<Integer> current, List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(current));
            return;
        }
        if (target < 0 || start >= numbers.length) {
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            // Include the current number
            current.add(numbers[i]);
            findCombinations(target - numbers[i], numbers, i + 1, current, combinations);
            // Exclude the current number (backtrack)
            current.remove(current.size() - 1);
        }
    }
}