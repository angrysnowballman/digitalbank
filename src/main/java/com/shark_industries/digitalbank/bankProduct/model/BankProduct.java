package com.shark_industries.digitalbank.bankProduct.model;

import com.shark_industries.digitalbank.authservice.model.User;
import com.shark_industries.digitalbank.bankProduct.productState.ProductState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Double productPrice;
    private ProductState productState;
    @Id
    private Long productOwnerId;
    private User productOwner;
}
