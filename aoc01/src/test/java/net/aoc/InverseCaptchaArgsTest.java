package net.aoc;

import static net.aoc.InverseCaptchaArgs.CAPTCHA_OPTION;
import static net.aoc.InverseCaptchaArgs.CYCLE_FACTOR_OPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

public class InverseCaptchaArgsTest {

    InverseCaptchaArgs testInstance = new InverseCaptchaArgs();

    @ParameterizedTest(name = "Given args {0} should initialize args data as {1}")
    @MethodSource("initializationDataFromArguments")
    public void shouldInitializeDataFromArguments(String[] args, InverseCaptchaArgs expectedArgumentData) {
        testInstance.processArguments(args);
        assertThat(testInstance).isEqualTo(expectedArgumentData);
    }

    static Stream<Arguments> initializationDataFromArguments() {
        return Stream.of(
                Arguments.of(null, InverseCaptchaArgs.builder().captcha(null).cycleFactor(null).build()),
                Arguments.of(new String[] {CAPTCHA_OPTION, ""}, InverseCaptchaArgs.builder().captcha(new int[] {}).cycleFactor(null).build()),
                Arguments.of(new String[] {CAPTCHA_OPTION, "1234"}, InverseCaptchaArgs.builder().captcha(new int[] { 1, 2, 3, 4}).cycleFactor(null).build()),
                Arguments.of(new String[] {CYCLE_FACTOR_OPTION, "1"}, InverseCaptchaArgs.builder().captcha(null).cycleFactor(1).build()),
                Arguments.of(new String[] {CAPTCHA_OPTION, "1234", CYCLE_FACTOR_OPTION, "1"}, InverseCaptchaArgs.builder().captcha(new int[] { 1, 2, 3, 4}).cycleFactor(1).build())
        );
    }

    @ParameterizedTest(name = "Expect a ParameterException to be thrown when given arguments {0}")
    @MethodSource("invalidInitializationDataArguments")
    public void shouldThrowParameterExceptionWhenProvidedArgumentsAreInvalid(String[] invalidArgs) {
        assertThatThrownBy(() -> testInstance.processArguments(invalidArgs)).isInstanceOf(ParameterException.class);
    }

    static Stream<Arguments> invalidInitializationDataArguments() {
        return Stream.of(
                Arguments.of((Object)new String[] {""}),
                Arguments.of((Object)new String[] {"1234"}),
                Arguments.of((Object)new String[] {CAPTCHA_OPTION}),
                Arguments.of((Object)new String[] {CYCLE_FACTOR_OPTION}),
                Arguments.of((Object)new String[] {CYCLE_FACTOR_OPTION, ""})
        );
    }

    @Test
    public void shouldPrintUsage() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try{
            testInstance.printUsage();
            assertThat(outContent.toString()).contains("Usage:  [options]");
            assertThat(outContent.toString()).contains(CAPTCHA_OPTION);
            assertThat(outContent.toString()).contains(CYCLE_FACTOR_OPTION);
        }finally{
            System.setOut(null);
        }
    }
}
