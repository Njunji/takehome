package com.example.unique_product.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UniqueProductService {

    public String findFirstUniqueProduct(String[] products) {
        if (products == null || products.length == 0) {
            return null;
        }

        // Use LinkedHashMap to maintain insertion order
        Map<String, Integer> productCount = new LinkedHashMap<>();

        // Count occurrences of each product
        for (String product : products) {
            productCount.merge(product, 1, Integer::sum);
        }

        // Find first product with count 1
        for (Map.Entry<String, Integer> entry : productCount.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null;
    }
}