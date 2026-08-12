package com.shark_industries.digitalbank.authservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Data
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @Column(name = "firstname")
    private String username;
    @Column(name = "lastname")
    private String lastname;
    @Column
    private String password;
}
