package com.shark_industries.digitalbank.accountservice.model;

import com.shark_industries.digitalbank.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByFirstname(String username);
}
