package com.shark_industries.digitalbank.accountservice.model;

import com.shark_industries.digitalbank.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByusername(String username);
}
