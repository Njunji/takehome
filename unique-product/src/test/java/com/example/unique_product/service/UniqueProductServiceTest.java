package com.example.unique_product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UniqueProductServiceTest {

    @Autowired
    private UniqueProductService uniqueProductService;

    @Test
    void testFindFirstUniqueProduct() {
        String[] products = {"Apple", "Computer", "Apple", "Bag"};
        assertEquals("Computer", uniqueProductService.findFirstUniqueProduct(products));
    }

    @Test
    void testNoUniqueProduct() {
        String[] products = {"Apple", "Apple", "Bag", "Bag"};
        assertNull(uniqueProductService.findFirstUniqueProduct(products));
    }

    @Test
    void testEmptyArray() {
        String[] products = {};
        assertNull(uniqueProductService.findFirstUniqueProduct(products));
    }

    @Test
    void testNullArray() {
        assertNull(uniqueProductService.findFirstUniqueProduct(null));
    }

    @Test
    void testSingleProduct() {
        String[] products = {"Apple"};
        assertEquals("Apple", uniqueProductService.findFirstUniqueProduct(products));
    }
}
