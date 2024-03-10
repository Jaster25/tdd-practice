package com.js.passwordchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    private final PasswordChecker passwordChecker = new PasswordChecker();

    @DisplayName("인자가 null인 경우 예외 발생")
    @Test
    void nullInput() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class,
                () -> passwordChecker.check(null));
    }

    @DisplayName("인자가 빈 값인 경우 예외 발생")
    @Test
    void emptyInput() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class,
                () -> passwordChecker.check(""));
    }

    @DisplayName("암호 강도: 강함")
    @Test
    void meetAllRules() {
        // given

        // when
        PasswordStrength result1 = passwordChecker.check("abcABC123");
        PasswordStrength result2 = passwordChecker.check("1ab2cA3BC");

        // then
        assertEquals(PasswordStrength.STRONG, result1);
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @DisplayName("암호 강도: 보통(길이가 8 미만)")
    @Test
    void meetDigitAndUppercaseRules() {
        // given

        // when
        PasswordStrength result1 = passwordChecker.check("aBC123");
        PasswordStrength result2 = passwordChecker.check("1B3C2");

        // then
        assertEquals(PasswordStrength.NORMAL, result1);
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @DisplayName("암호 강도: 보통(대문자 없음)")
    @Test
    void meetDigitAndLengthRules() {
        // given

        // when
        PasswordStrength result1 = passwordChecker.check("abcd12345");
        PasswordStrength result2 = passwordChecker.check("12ab34cde");

        // then
        assertEquals(PasswordStrength.NORMAL, result1);
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @DisplayName("암호 강도: 보통(숫자 없음)")
    @Test
    void meetUppercaseAndLengthRules() {
        // given

        // when
        PasswordStrength result1 = passwordChecker.check("abcdABCD");
        PasswordStrength result2 = passwordChecker.check("aAbBcCdDeE");

        // then
        assertEquals(PasswordStrength.NORMAL, result1);
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @DisplayName("암호 강도: 약함(길이만 충족)")
    @Test
    void meetOnlyLengthRule() {
        // given

        // when
        PasswordStrength result1 = passwordChecker.check("aaaabbbb");
        PasswordStrength result2 = passwordChecker.check("ababababcd");

        // then
        assertEquals(PasswordStrength.WEAK, result1);
        assertEquals(PasswordStrength.WEAK, result2);
    }

    @DisplayName("암호 강도: 약함(대문자만 충족)")
    @Test
    void meetOnlyUppercaseRule() {
        // given

        // when
        PasswordStrength result1 = passwordChecker.check("aaBB");
        PasswordStrength result2 = passwordChecker.check("AABBCC");

        // then
        assertEquals(PasswordStrength.WEAK, result1);
        assertEquals(PasswordStrength.WEAK, result2);
    }
}
