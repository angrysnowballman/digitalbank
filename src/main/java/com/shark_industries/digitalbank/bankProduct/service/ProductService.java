package com.shark_industries.digitalbank.bankProduct.service;

import com.shark_industries.digitalbank.bankProduct.model.BankProduct;
import com.shark_industries.digitalbank.bankProduct.model.ProductRepository;
import com.shark_industries.digitalbank.bankProduct.productState.ProductState;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public BankProduct createProduct(BankProduct bankProduct) {
        if (bankProduct != null) {
            throw new RuntimeException("Product cannot be null");
        }

        BankProduct product = BankProduct.builder()
                .productId(bankProduct.getProductId())
                .productName(bankProduct.getProductName())
                .productPrice(bankProduct.getProductPrice())
                .productState(bankProduct.getProductState())
                .productOwnerId(bankProduct.getProductOwnerId())
                .productOwner(bankProduct.getProductOwner())
                .build();

        return product;

    }

    public BankProduct getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void changeStateProduct(Long productId, ProductState state) {
        getProductById(productId).setProductState(state);
    }
}
