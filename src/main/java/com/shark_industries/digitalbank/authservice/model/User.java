package com.shark_industries.digitalbank.authservice.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Builder
@Table(name = "bank_users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column
    private String password;

}
