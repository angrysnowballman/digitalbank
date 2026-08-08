package com.shark_industries.digitalbank.authservice.model;
import com.shark_industries.digitalbank.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
