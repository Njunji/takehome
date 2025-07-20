package com.example.closestminimums.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClosestMinimumsService {

    public Integer findClosestMinimumsDistance(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }

        // Find the minimum value
        int minValue = numbers[0];
        for (int num : numbers) {
            if (num < minValue) {
                minValue = num;
            }
        }

        // Collect indices of minimum values
        List<Integer> minIndices = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == minValue) {
                minIndices.add(i);
            }
        }

        // Find minimum distance between consecutive indices
        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < minIndices.size(); i++) {
            int distance = minIndices.get(i) - minIndices.get(i - 1);
            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance == Integer.MAX_VALUE ? null : minDistance;
    }
}