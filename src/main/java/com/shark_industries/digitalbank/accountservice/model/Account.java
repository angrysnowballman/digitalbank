package com.shark_industries.digitalbank.accountservice.model;

import com.shark_industries.digitalbank.accountservice.enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
@Data
@Builder
@Table(name = "accounts")  // ← здесь
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private UUID uuid;
    private String firstname;
    private String lastname;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}