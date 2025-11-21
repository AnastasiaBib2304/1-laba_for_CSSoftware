package com.example.demo.service;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

    public boolean isValid(String password) {
        if (password == null || password.length() < MIN_LENGTH) {
            return false;
        }

        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasSpecialChar = SPECIAL_CHAR_PATTERN.matcher(password).find();

        return hasUpperCase && hasLowerCase && hasSpecialChar;
    }

    public String getPasswordRequirements() {
        return "Password must be at least " + MIN_LENGTH + " characters long, " +
                "contain at least one uppercase letter, one lowercase letter, " +
                "and one special character";
    }
}