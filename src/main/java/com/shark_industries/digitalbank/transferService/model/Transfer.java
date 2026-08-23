package com.shark_industries.digitalbank.transferService.model;


import com.shark_industries.digitalbank.accountservice.model.Account;
import com.shark_industries.digitalbank.transferService.enums.StatusTransfer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Data
@Builder
public class Transfer {
    @Id
    //подсмотрел
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID transferId;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    private BigDecimal amount;
    private Enum<StatusTransfer> status;
    private String errorMessage;

    private LocalDateTime createdAt;


}
