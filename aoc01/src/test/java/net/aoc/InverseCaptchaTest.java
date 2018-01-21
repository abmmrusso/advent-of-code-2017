package net.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class InverseCaptchaTest {

    private InverseCaptcha testInstance = new InverseCaptcha();

    @Test
    public void captchaForNullDigitSequenceShouldBeZero(){
        assertThat(testInstance.captchaSolution(null, 1)).isEqualTo(0);
    }

    @Test
    public void captchaForEmptyDigitSequenceShouldBeZero() {
        assertThat(testInstance.captchaSolution(new int[]{}, 1)).isEqualTo(0);
    }

    @Test
    public void captchaForOddSizedDigitSequenceThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> testInstance.captchaSolution(new int[] {1}, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "sequence: {0} | cycleFactor: {1}")
    @MethodSource("nonRepeatingDigitSequences")
    public void captchaForNonRepeatingDigitSequenceShouldBeZero(int[] captcha, int captchaCycleFactor) {
        assertThat(testInstance.captchaSolution(captcha, captchaCycleFactor)).isEqualTo(0);
    }

    static Stream<Arguments> nonRepeatingDigitSequences(){
        return Stream.of(
                Arguments.of(new int[] {1, 2}, 1),
                Arguments.of(new int[] {1, 2, 3, 4}, 1),
                Arguments.of(new int[] {1, 2, 3, 4}, 2),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 1),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 2),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 3)
        );
    }

    @ParameterizedTest(name = "sequence: {0} | cycleFactor: {1} | expectedSum: {2}")
    @MethodSource("repeatingDigitSequencesWithGivenCycleFactor")
    public void shouldReturnExpectedCaptchaForGivenDigitSequencesContainingRepeatingDigitSequencesWithGivenCycleFactor(int[] captcha, int captchaCycleFactor, int expectedSum) {
        assertThat(testInstance.captchaSolution(captcha, captchaCycleFactor)).isEqualTo(expectedSum);
    }

    static Stream<Arguments> repeatingDigitSequencesWithGivenCycleFactor(){
        return Stream.of(
                Arguments.of(new int[] {1, 1}, 1, 2),
                Arguments.of(new int[] {1, 1, 2, 2}, 1, 3),
                Arguments.of(new int[] {1, 2, 1, 2}, 2, 6),
                Arguments.of(new int[] {1, 2, 2, 1}, 3, 3),
                Arguments.of(new int[] {1, 2, 3, 1}, 3, 1),
                Arguments.of(new int[] {1, 1, 2, 2, 3, 3}, 1, 6),
                Arguments.of(new int[] {1, 2, 1, 2, 1, 2}, 2, 9),
                Arguments.of(new int[] {1, 2, 3, 4, 2, 5}, 3, 4),
                Arguments.of(new int[] {1, 2, 3, 1, 2, 3}, 3, 12),
                Arguments.of(new int[] {1, 2, 1, 3, 1, 4, 1, 5}, 4, 4)
        );
    }
}
