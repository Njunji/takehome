package com.example.closestminimums.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClosestMinimumsServiceTest {

    @Autowired
    private ClosestMinimumsService closestMinimumsService;

    @Test
    void testFindClosestMinimumsDistance() {
        int[] numbers = {1, 2, 3, 1, 4, 5, 2};
        assertEquals(3, closestMinimumsService.findClosestMinimumsDistance(numbers));
    }

    @Test
    void testMultipleMinimums() {
        int[] numbers = {1, 2, 1, 3, 1, 4};
        assertEquals(2, closestMinimumsService.findClosestMinimumsDistance(numbers));
    }

    @Test
    void testAdjacentMinimums() {
        int[] numbers = {1, 1, 2, 3, 4};
        assertEquals(1, closestMinimumsService.findClosestMinimumsDistance(numbers));
    }

    @Test
    void testNullArray() {
        assertNull(closestMinimumsService.findClosestMinimumsDistance(null));
    }

    @Test
    void testSingleElement() {
        int[] numbers = {1};
        assertNull(closestMinimumsService.findClosestMinimumsDistance(numbers));
    }
}