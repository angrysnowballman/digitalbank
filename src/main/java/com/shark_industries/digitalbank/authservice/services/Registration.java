package com.shark_industries.digitalbank.authservice.services;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class Registration {
    Scanner scanner = new Scanner(System.in);
    String username = scanner.nextLine();
    String password = scanner.nextLine();
    public void register() {


        System.out.printf("Введите имя пользователя: " + username);
        System.out.printf("Введите пароль: " + password);
    }
}
