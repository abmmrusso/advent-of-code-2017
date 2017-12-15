package net.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class InverseCaptchaTest {

    private InverseCaptcha testInstance = new InverseCaptcha();

    @Test
    public void captchaForNullDigitSequenceShouldBeZero(){
        assertThat(testInstance.captchaSolution(null)).isEqualTo(0);
    }

    @Test
    public void captchaForEmptyDigitSequenceShouldBeZero() {
        assertThat(testInstance.captchaSolution(new int[]{})).isEqualTo(0);
    }

    @Test
    public void captchaForOddSizedDigitSequenceThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> testInstance.captchaSolution(new int[] {1}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "sequence: {0}")
    @MethodSource("nonRepeatingDigitSequences")
    public void captchaForNonRepeatingDigitSequenceShouldBeZero(int[] captcha) {
        assertThat(testInstance.captchaSolution(captcha)).isEqualTo(0);
    }

    static Stream<Arguments> nonRepeatingDigitSequences(){
        return Stream.of(
                Arguments.of(new int[] {1, 2}),
                Arguments.of(new int[] {1, 2, 3, 4}),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6})
        );
    }

    @ParameterizedTest(name = "sequence: {0} | expectedSum: {1}")
    @MethodSource("repeatingDigitSequences")
    public void shouldReturnExpectedCaptchaForGivenDigitSequencesContainingRepeatingDigitSequences(int[] captcha, int expectedSum) {
        assertThat(testInstance.captchaSolution(captcha)).isEqualTo(expectedSum);
    }

    static Stream<Arguments> repeatingDigitSequences(){
        return Stream.of(
                Arguments.of(new int[] {1, 1}, 2),
                Arguments.of(new int[] {1, 2, 1, 2}, 6),
                Arguments.of(new int[] {1, 2, 3, 4, 2, 5}, 4),
                Arguments.of(new int[] {1, 2, 3, 1, 2, 3}, 12),
                Arguments.of(new int[] {1, 2, 1, 3, 1, 4, 1, 5}, 4)
        );
    }
}
