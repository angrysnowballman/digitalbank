//package com.shark_industries.digitalbank;
//
//import com.shark_industries.digitalbank.api.controller.AuthRequest;
//import com.shark_industries.digitalbank.authservice.model.User;
//import com.shark_industries.digitalbank.authservice.model.UserRepository;
//import com.shark_industries.digitalbank.authservice.services.AuthService;
//import com.shark_industries.digitalbank.authservice.services.JwtService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class DigitalbankApplicationTests {
//    @InjectMocks
//    private AuthService authService;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Mock
//    private JwtService jwtService;
//

//    @Test
//    void loginVars() throws IllegalAccessError {
////        User user1 = User.builder().firstname(" ").password("              ").build();
////        User user2 = User.builder().firstname(" @34").password("@^*5!!!@#@$&*(34").build();
//        User user = User.builder()
//                .firstname("Vitaliy")
//                .password("encodedPassword")
//                .build();
//
//        when(userRepository.findByFirstname("Vitaliy"))
//                .thenReturn(Optional.of(user));
//
//        when(passwordEncoder.matches(
//                "wrongpassword",
//                "encodedPassword"
//        )).thenReturn(false);
//
//        String result = authService.login(
//                new AuthRequest("Vitaliy", "wrongpassword")
//        );
//
//        assertEquals("Wrong password", result);
//    }
//
//}
