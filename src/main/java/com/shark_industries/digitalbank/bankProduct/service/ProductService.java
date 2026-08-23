package com.shark_industries.digitalbank.bankProduct.service;

import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.authservice.model.UserRepository;
import com.shark_industries.digitalbank.bankProduct.model.BankProduct;
import com.shark_industries.digitalbank.bankProduct.model.ProductRepository;
import com.shark_industries.digitalbank.bankProduct.productState.ProductState;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        // Бред?
        this.userRepository = userRepository;
    }

//    public BankProduct createProduct(BankProduct bankProduct) {
//        if (bankProduct != null) {
//            throw new RuntimeException("Product cannot be null");
//        }
//
//        BankProduct product = BankProduct.builder()
//                .productId(bankProduct.getProductId())
//                .productName(bankProduct.getProductName())
//                .productPrice(bankProduct.getProductPrice())
//                .productState(bankProduct.getProductState())
//                .productOwnerId(bankProduct.getProductOwnerId())
//                .productOwner(bankProduct.getProductOwner())
//                .build();
//
//        return product;
//
//    }

    public BankProduct getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void changeStateProduct(Long productId, ProductState state) {
        getProductById(productId).setProductState(state);
    }

    public BankProduct createProduct(Long userId, String productName,  Double productPrice) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new BankProduct();
        }
            BankProduct bankProduct = BankProduct.builder()
                    .productName(productName)
                    .productPrice(productPrice)
                    .productState(ProductState.ACTIVE)
                    .productOwner(user)
                    .build();
        return productRepository.save(bankProduct);
    }

    public BankProduct closeProduct(Long productId) {
        BankProduct bankProduct = getProductById(productId);  // ← здесь нужна ;
        if (bankProduct == null) {
            return new BankProduct();
        }
        bankProduct.setProductState(ProductState.CLOSED);
        return productRepository.save(bankProduct);
    }

}