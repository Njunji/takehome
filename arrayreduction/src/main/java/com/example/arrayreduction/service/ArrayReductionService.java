package com.example.arrayreduction.service;

import org.springframework.stereotype.Service;

@Service
public class ArrayReductionService {

    public int canMakeAllZeroExceptFirst(int[] a) {
        if (a == null || a.length <= 1) {
            return 1; // Single element or empty array is already valid
        }

        // Check if all elements are positive
        for (int num : a) {
            if (num <= 0) {
                return 0; // Non-positive elements make it impossible
            }
        }

        // Create a working copy of the array
        long[] arr = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            arr[i] = a[i];
        }

        // Repeatedly apply the operation until no further progress is possible
        boolean changed;
        do {
            changed = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > 0) {
                    arr[i] = arr[i] - arr[i - 1];
                    if (arr[i] < 0) {
                        return 0; // Negative value means impossible
                    }
                    changed = true;
                }
            }
        } while (changed);

        // Check if all elements except the first are zero
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                return 0;
            }
        }

        return 1;
    }
}