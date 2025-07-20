package com.example.unique_product.controller;

import com.example.unique_product.dto.UniqueProductResponse;
import com.example.unique_product.service.UniqueProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class UniqueProductController {

    private final UniqueProductService uniqueProductService;

    @PostMapping("/unique")
    public UniqueProductResponse findFirstUnique(@RequestBody String[] products) {
        String result = uniqueProductService.findFirstUniqueProduct(products);
        return new UniqueProductResponse(result);
    }
}
