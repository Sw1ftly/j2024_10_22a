package com.example.demo;

public class PasswordValidator {

    public static boolean isStrongPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }
}