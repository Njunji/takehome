package com.example.listrotation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListRotationServiceTest {

    @Autowired
    private ListRotationService listRotationService;

    @Test
    void testRotateListExample() {
        String[] values = {"ID_A01", "ID_A02", "ID_A03", "ID_A04", "ID_A05", "ID_A06"};
        int n = 2;
        String[] expected = {"ID_A05", "ID_A06", "ID_A01", "ID_A02", "ID_A03", "ID_A04"};
        assertArrayEquals(expected, listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListEmpty() {
        String[] values = {};
        int n = 2;
        assertArrayEquals(new String[0], listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListNull() {
        String[] values = null;
        int n = 2;
        assertArrayEquals(new String[0], listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListSingleElement() {
        String[] values = {"ID_A01"};
        int n = 2;
        assertArrayEquals(new String[]{"ID_A01"}, listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListZeroRotation() {
        String[] values = {"ID_A01", "ID_A02", "ID_A03"};
        int n = 0;
        assertArrayEquals(new String[]{"ID_A01", "ID_A02", "ID_A03"}, listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListFullRotation() {
        String[] values = {"ID_A01", "ID_A02", "ID_A03"};
        int n = 3;
        assertArrayEquals(new String[]{"ID_A01", "ID_A02", "ID_A03"}, listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListLargeN() {
        String[] values = {"ID_A01", "ID_A02", "ID_A03", "ID_A04"};
        int n = 6; // Equivalent to 2 rotations (6 % 4 = 2)
        assertArrayEquals(new String[]{"ID_A03", "ID_A04", "ID_A01", "ID_A02"}, listRotationService.rotateList(values, n));
    }

    @Test
    void testRotateListNegativeN() {
        String[] values = {"ID_A01", "ID_A02", "ID_A03"};
        int n = -1;
        assertArrayEquals(new String[0], listRotationService.rotateList(values, n));
    }
}