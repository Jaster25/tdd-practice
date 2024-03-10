package com.js.passwordchecker;

public class PasswordChecker {

    public PasswordStrength check(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }

        boolean lengthRule = greaterThanOrEqualTo8(password);
        if (!lengthRule) {
            return PasswordStrength.NORMAL;
        }

        boolean uppercaseRule = containsUppercase(password);
        if (!uppercaseRule) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean greaterThanOrEqualTo8(String password) {
        return password.length() >= 8;
    }

    private boolean containsUppercase(String password) {
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
