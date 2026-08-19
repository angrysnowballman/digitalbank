package com.shark_industries.digitalbank.bankProduct.controller;


import com.shark_industries.digitalbank.bankProduct.model.BankProduct;
import com.shark_industries.digitalbank.bankProduct.productState.ProductState;
import com.shark_industries.digitalbank.bankProduct.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    //CreateProduct (id)

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BankProduct> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<?> updateProductStatus(@PathVariable Long id, @PathVariable ProductState status) {
        productService.changeStateProduct(id, status);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
