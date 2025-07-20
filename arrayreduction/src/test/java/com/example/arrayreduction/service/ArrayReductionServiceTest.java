package com.example.arrayreduction.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArrayReductionServiceTest {

    @Autowired
    private ArrayReductionService arrayReductionService;

    @Test
    void testCanMakeAllZeroExceptFirstExample() {
        int[] array = {1, 2, 3};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testSingleElement() {
        int[] array = {5};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testNullArray() {
        int[] array = null;
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testImpossibleCase() {
        int[] array = {2, 1, 3}; // Leads to negative values
        assertEquals(0, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testNonPositiveElement() {
        int[] array = {1, 0, 3};
        assertEquals(0, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testNegativeElement() {
        int[] array = {1, -1, 2};
        assertEquals(0, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testAlreadyZeroExceptFirst() {
        int[] array = {5, 0, 0, 0};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testComplexCase() {
        int[] array = {1, 3, 4, 2, 1};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testLargeArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testLargeValues() {
        int[] array = {1, 1000, 1000, 1000};
        assertEquals(1, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }

    @Test
    void testImpossibleLargeDifference() {
        int[] array = {1, 3, 1, 3}; // Leads to negative values
        assertEquals(0, arrayReductionService.canMakeAllZeroExceptFirst(array));
    }
}