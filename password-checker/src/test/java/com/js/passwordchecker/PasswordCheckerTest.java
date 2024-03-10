package com.js.passwordchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    @DisplayName("인자가 null인 경우 예외 발생")
    @Test
    void nullInput() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class,
                () -> new PasswordChecker().check(null));
    }

    @DisplayName("인자가 빈 값인 경우 예외 발생")
    @Test
    void emptyInput() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class,
                () -> new PasswordChecker().check(""));
    }

    @DisplayName("모든 조건을 충족하면 암호 강도 강함")
    @Test
    void meetAllRules() {
        // given

        // when
        PasswordStrength passwordStrength = new PasswordChecker().check("abcABC123");

        // then
        assertEquals(PasswordStrength.STRONG, passwordStrength);
    }
}
