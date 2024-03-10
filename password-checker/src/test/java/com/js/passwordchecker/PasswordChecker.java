package com.js.passwordchecker;

public class PasswordChecker {

    public PasswordStrength check(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }

        boolean lengthRule = greaterThanOrEqualTo8(password);
        boolean uppercaseRule = containsUppercase(password);
        boolean digitRule = containsDigit(password);

        int metCount = 0;
        if (lengthRule) {
            metCount += 1;
        }
        if (uppercaseRule) {
            metCount += 1;
        }
        if (digitRule) {
            metCount += 1;
        }

        if (metCount == 1 || metCount == 0) {
            return PasswordStrength.WEAK;
        }
        if (metCount == 2) {
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

    private boolean containsDigit(String password) {
        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }
}
