package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationTests {

    @Test
    public void testStrongPassword() {
        // Test valid strong password
        assertTrue(PasswordValidator.isStrongPassword("Strong123"));

        // Test password with less than 8 characters
        assertFalse(PasswordValidator.isStrongPassword("Short1"));

        // Test password with no letters
        assertFalse(PasswordValidator.isStrongPassword("12345678"));

        // Test password with no digits
        assertFalse(PasswordValidator.isStrongPassword("NoDigitsHere"));

        // Test valid strong password with special characters
        assertTrue(PasswordValidator.isStrongPassword("Strong!123"));
    }

    @Test
    public void testValidEmail() {
        // Test valid email addresses
        assertTrue(EmailValidator.isValidEmail("valid.email@example.com"));
        assertTrue(EmailValidator.isValidEmail("user+name@example.co"));

        // Test invalid email addresses
        assertFalse(EmailValidator.isValidEmail("invalid-email.com")); // missing '@'
        assertFalse(EmailValidator.isValidEmail("@example.com")); // missing username

        
    }
}