package com.shark_industries.digitalbank.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
        //TODO:  объяснить
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/register").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        // ВАЖНО: Это должен быть ТОЧНО ТОТ ЖЕ секрет, который вы используете в JwtService для генерации токена
        String secretString = "your-very-long-secret-key-here-must-be-at-least-256-bits-long-for-hs256";

        byte[] secretBytes = secretString.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(secretBytes, "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
