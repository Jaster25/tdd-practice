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

        boolean uppercaseRule = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                uppercaseRule = true;
                break;
            }
        }
        if (!uppercaseRule) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }
}
