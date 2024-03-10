package com.js.passwordchecker;

public class PasswordChecker {

    public PasswordStrength check(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }

        boolean lengthRule = password.length() >= 8;
        if (!lengthRule) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }
}
