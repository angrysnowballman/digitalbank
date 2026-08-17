package com.shark_industries.digitalbank.accountservice.model;

import com.shark_industries.digitalbank.accountservice.enums.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Entity
@Data
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long accountId;
    private String firstname;
    private String lastname;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
