package net.aoc;

import java.util.stream.IntStream;

public class InverseCaptcha {
    public int captchaSolution(int[] captcha) {
        if(captcha == null || captcha.length == 0) {
            return 0;
        }

        if(captcha.length % 2 != 0) {
            throw new IllegalArgumentException();
        }

        int captchaHalfSize = captcha.length / 2;

        return IntStream.range(0, captcha.length)
                .map(idx -> captcha[idx] == captcha[(idx + captchaHalfSize)%captcha.length]?captcha[idx]:0)
                .sum();
    }
}
