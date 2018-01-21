package net.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptchaStringConverterTest {

    @Test
    public void shouldConvertNullCaptchaStringToNullCaptchaArrayOfIntegers() {
        assertThat(new CaptchaStringConverter().convert(null)).isEqualTo(null);
    }

    @Test
    public void shouldConvertEmptyCaptchaStringToEmptyCaptchaArrayOfIntegers() {
        assertThat(new CaptchaStringConverter().convert("")).isEqualTo(new int[] {});
    }

    @ParameterizedTest(name = "Should convert Captcha String \"{0}\" to Captcha array of integers {1}")
    @MethodSource("dataForCaptchaStringConvertionToCaptchaIntArrayTest")
    public void shouldConvertEmptyCaptchaStringToEmptyCaptchaArrayOfIntegers(String stringCaptcha, int[] expectedIntArrayCaptcha) {
        assertThat(new CaptchaStringConverter().convert("")).isEqualTo(new int[] {});
    }

    static Stream<Arguments> dataForCaptchaStringConvertionToCaptchaIntArrayTest() {
        return Stream.of(
                Arguments.of( "1", new int[] {1}),
                Arguments.of( "1234567890", new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0})
        );
    }
}
