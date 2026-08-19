package com.shark_industries.digitalbank.bankProduct.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<BankProduct, Long> {
    Optional<BankProduct> findByProductName(String productName);
}
