package com.js.passwordchecker;

public class PasswordChecker {

    public PasswordStrength check(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int metCount = calculateMetCount(password);

        if (metCount == 0 || metCount == 1) {
            return PasswordStrength.WEAK;
        } else if (metCount == 2) {
            return PasswordStrength.NORMAL;
        } else {
            return PasswordStrength.STRONG;
        }
    }

    private int calculateMetCount(String password) {
        int metCount = 0;
        if (greaterThanOrEqualTo8(password)) {
            metCount += 1;
        }
        if (containsUppercase(password)) {
            metCount += 1;
        }
        if (containsDigit(password)) {
            metCount += 1;
        }
        return metCount;
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
