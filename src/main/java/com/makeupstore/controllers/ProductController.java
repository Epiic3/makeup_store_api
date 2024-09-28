package com.makeupstore.controllers;

import com.makeupstore.models.ProductEntity;
import com.makeupstore.services.products.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/by/{name}")
    public ResponseEntity<?> getAllProducts(@PathVariable String name) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<ProductEntity> products = productService.getProductsByName(name);
            response.put("message", "success");
            response.put("data", products);

            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.put("message", "error");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
