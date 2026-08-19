package com.shark_industries.digitalbank.bankProduct.controller;


import com.shark_industries.digitalbank.bankProduct.model.BankProduct;
import com.shark_industries.digitalbank.bankProduct.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final BankProduct bankProduct;
    private final ProductService productService;

    public ProductController(BankProduct bankProduct, ProductService productService) {
        this.bankProduct = bankProduct;
        this.productService = productService;
    }

    @GetMapping("/products/get/{id}")
    public Long getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }


}
