package com.shark_industries.digitalbank.transferService.model;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(UUID toAccount, UUID fromAccount, BigDecimal amount) {
}
