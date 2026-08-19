package com.shark_industries.digitalbank.bankProduct.service;

import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.bankProduct.model.BankProduct;
import com.shark_industries.digitalbank.bankProduct.productState.ProductState;
import jakarta.persistence.Id;


public class ProductService {

    private final BankProduct bankProduct;

    public ProductService(BankProduct bankProduct) {
        this.bankProduct = bankProduct;
    }


    public Long getProductById(Long id) {
        return bankProduct.;
    }

    public void terminateProduct(String productId) {
        getProduct(productId).setStatus(ProductState.CLOSED);
    }
}
