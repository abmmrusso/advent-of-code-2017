package net.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @ParameterizedTest(name = "sequence: {0}")
    @MethodSource("nonRepeatingDigitSequences")
    public void captchaForNonRepeatingDigitSequenceShouldBeZero(int[] captcha) {
        assertThat(testInstance.captchaSolution(captcha)).isEqualTo(0);
    }

    static Stream<Arguments> nonRepeatingDigitSequences(){
        return Stream.of(
                Arguments.of(new int[] {1}),
                Arguments.of(new int[] {2}),
                Arguments.of(new int[] {3}),
                Arguments.of(new int[] {4}),
                Arguments.of(new int[] {5}),
                Arguments.of(new int[] {6}),
                Arguments.of(new int[] {7}),
                Arguments.of(new int[] {8}),
                Arguments.of(new int[] {9}),
                Arguments.of(new int[] {1, 2}),
                Arguments.of(new int[] {1, 2, 3}),
                Arguments.of(new int[] {1, 2, 3, 4})
        );
    }

    @ParameterizedTest(name = "sequence: {0} | expectedSum: {1}")
    @MethodSource("repeatingDigitSequences")
    public void shouldReturnExpectedCaptchaForGivenDigitSequencesContainingRepeatingDigitSequences(int[] captcha, int expectedSum) {
        assertThat(testInstance.captchaSolution(captcha)).isEqualTo(expectedSum);
    }

    static Stream<Arguments> repeatingDigitSequences(){
        return Stream.of(
                Arguments.of(new int[] {1, 1, 2}, 1),
                Arguments.of(new int[] {2, 2, 3}, 2),
                Arguments.of(new int[] {1, 1, 2, 2}, 3),
                Arguments.of(new int[] {1, 1, 1, 2}, 2),
                Arguments.of(new int[] {1, 1, 1, 1, 2}, 3)
        );
    }

    @ParameterizedTest(name = "sequence: {0} | expectedSum: {1}")
    @MethodSource("circularRepeatingDigitSequences")
    public void shouldReturnExpectedCaptchaForGivenDigitSequencesContainingCircularRepeatingDigitSequences(int[] captcha, int expectedSum) {
        assertThat(testInstance.captchaSolution(captcha)).isEqualTo(expectedSum);
    }

    static Stream<Arguments> circularRepeatingDigitSequences(){
        return Stream.of(
                Arguments.of(new int[] {1, 1}, 2),
                Arguments.of(new int[] {2, 2, 2}, 6),
                Arguments.of(new int[] {9, 1, 2, 9}, 9)
        );
    }
}
