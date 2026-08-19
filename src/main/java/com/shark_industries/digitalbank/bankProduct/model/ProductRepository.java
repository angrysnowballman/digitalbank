package com.shark_industries.digitalbank.bankProduct.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<BankProduct, Long> {
    Optional<BankProduct> findByProductCode(String productName);
}
