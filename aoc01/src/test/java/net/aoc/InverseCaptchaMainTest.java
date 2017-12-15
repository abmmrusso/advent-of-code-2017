package net.aoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

public class InverseCaptchaMainTest {

    private InverseCaptcha mockInvertedCaptcha = mock(InverseCaptcha.class);
    private InverseCaptchaMain testInstance = new InverseCaptchaMain(mockInvertedCaptcha);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @ParameterizedTest(name = "Input digit sequence: {0} | expected captcha: {1}")
    @MethodSource("inputData")
    public void shouldOutputInverseCaptchaForGivenDigitSequence(String inputDigitSequence, int expectedCaptcha) {
        final int[] inputDigitArray = inputDigitSequence == null? null: inputDigitSequence.chars().mapToObj(c -> (char)c).mapToInt(c -> Integer.parseInt("" + c)).toArray();
        when(mockInvertedCaptcha.captchaSolution(inputDigitArray)).thenReturn(expectedCaptcha);
        testInstance.inverseCaptcha(inputDigitSequence);
        assertThat(outContent.toString()).isEqualTo(String.format("%d%n", expectedCaptcha));
        verify(mockInvertedCaptcha).captchaSolution(inputDigitArray);
    }

    static Stream<Arguments> inputData() {
        return Stream.of(
                Arguments.of(null, 0),
                Arguments.of("", 0),
                Arguments.of("1", 0),
                Arguments.of("1323", 6)
        );
    }
}
