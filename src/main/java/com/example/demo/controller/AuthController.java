package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PasswordValidator;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        // Валидация
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        if (request.getUsername().length() < 3 || request.getUsername().length() > 50) {
            return ResponseEntity.badRequest().body("Username must be between 3 and 50 characters");
        }

        if (!passwordValidator.isValid(request.getPassword())) {
            return ResponseEntity.badRequest()
                    .body("Password does not meet requirements: " + passwordValidator.getPasswordRequirements());
        }

        try {
            User user = new User();
            user.setUsername(request.getUsername().trim());

            // Назначаем роль: если указана в запросе, иначе ROLE_USER по умолчанию
            String role = (request.getRole() != null && !request.getRole().trim().isEmpty())
                    ? request.getRole()
                    : "ROLE_USER";
            user.setRoles(Collections.singletonList(role));

            User savedUser = userService.registerUser(user, request.getPassword());

            return ResponseEntity.ok("User registered successfully with ID: " + savedUser.getId() +
                    ", Role: " + role);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegistrationRequest request) {
        // Только для создания администраторов (можно добавить дополнительную проверку)
        try {
            User savedUser = userService.registerUserWithRole(
                    request.getUsername(),
                    request.getPassword(),
                    "ROLE_ADMIN"
            );

            return ResponseEntity.ok("Admin user registered successfully with ID: " + savedUser.getId());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/password-requirements")
    public ResponseEntity<String> getPasswordRequirements() {
        return ResponseEntity.ok(passwordValidator.getPasswordRequirements());
    }
}