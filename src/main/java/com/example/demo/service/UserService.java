package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Проверим, что роли загружаются
        System.out.println("Loading user: " + username + " with roles: " + user.getRoles());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(role -> {
                            // Убираем префикс ROLE_ если есть
                            String cleanRole = role.startsWith("ROLE_") ? role.substring(5) : role;
                            System.out.println("Granting role: " + cleanRole);
                            return cleanRole;
                        })
                        .toArray(String[]::new))
                .build();
    }

    public User registerUser(User user, String rawPassword) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    // Метод для регистрации с указанием роли
    public User registerUserWithRole(String username, String password, String role) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // Если роль не указана, назначается ROLE_USER по умолчанию
        String userRole = (role != null && !role.trim().isEmpty()) ? role : "ROLE_USER";
        user.setRoles(Collections.singletonList(userRole));

        return userRepository.save(user);
    }
}