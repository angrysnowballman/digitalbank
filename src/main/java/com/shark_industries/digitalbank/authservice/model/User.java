package com.shark_industries.digitalbank.authservice.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;


}
