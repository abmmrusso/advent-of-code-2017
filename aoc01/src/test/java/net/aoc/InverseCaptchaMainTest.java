package net.aoc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class InverseCaptchaMainTest {

    private InverseCaptcha mockInvertedCaptcha = mock(InverseCaptcha.class);
    private InverseCaptchaArgs testInvertedCaptchaArgs = new InverseCaptchaArgs();
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

    @Test
    public void shouldGenerateInvertedCaptchaWithDefaultCycleFactorOfHalfTheSizeOfCaptchaString() {
        final int[] captchaDigits = new int[] {1, 3, 2, 3};
        final int expectedCycleFactor = captchaDigits.length/2;
        final int expectedInverseCaptcha = 6;
        testInvertedCaptchaArgs.setCaptcha(captchaDigits);
        runInvertedCaptchaTestLogic(testInvertedCaptchaArgs, expectedCycleFactor, expectedInverseCaptcha);
    }

    @Test
    public void shouldGenerateInvertedCaptchaWithProvidedCycleFactorOfHalfTheSizeOfCaptchaString() {
        final int[] captchaDigits = new int[] {1, 1, 2, 2};
        final int expectedCycleFactor = 1;
        final int expectedInverseCaptcha = 3;
        testInvertedCaptchaArgs.setCaptcha(captchaDigits);
        testInvertedCaptchaArgs.setCycleFactor(expectedCycleFactor);
        runInvertedCaptchaTestLogic(testInvertedCaptchaArgs, expectedCycleFactor, expectedInverseCaptcha);
    }

    private void runInvertedCaptchaTestLogic(final InverseCaptchaArgs argsToUse, final int expectedCycleFactor, final int expectedInverseCaptcha) {
        when(mockInvertedCaptcha.captchaSolution(argsToUse.getCaptcha(), expectedCycleFactor)).thenReturn(expectedInverseCaptcha);
        testInstance.inverseCaptcha(testInvertedCaptchaArgs);
        assertThat(outContent.toString()).isEqualTo(String.format("%d%n", expectedInverseCaptcha));
        verify(mockInvertedCaptcha).captchaSolution(argsToUse.getCaptcha(), expectedCycleFactor);
    }

    @Test
    public void shouldTriggerUsageOutputIfWrongParameters() {
        InverseCaptchaMain.main(new String[] { InverseCaptchaArgs.CAPTCHA_OPTION });
        assertThat(outContent.toString()).contains(InverseCaptchaArgs.CAPTCHA_OPTION);
    }

    @Test
    public void endToEndInverseCaptchaGenerationTest() {
        InverseCaptchaMain.main(new String[] { InverseCaptchaArgs.CAPTCHA_OPTION, "1323"});
        assertThat(outContent.toString()).isEqualTo(String.format("%d%n", 6));
    }
}
