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

}
