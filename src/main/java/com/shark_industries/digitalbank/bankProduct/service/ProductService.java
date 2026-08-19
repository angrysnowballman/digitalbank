package com.shark_industries.digitalbank.bankProduct.service;

import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.bankProduct.model.BankProduct;
import com.shark_industries.digitalbank.bankProduct.productState.ProductState;
import jakarta.persistence.Id;


public class ProductService {

    private final BankProduct bankProduct;

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

        return prproduct;

    }



    public Long getProductById(Long id) {
        return product.getId();
    }

    public void terminateProduct(String productId) {
        getProduct(productId).setStatus(ProductState.CLOSED);
    }
}
