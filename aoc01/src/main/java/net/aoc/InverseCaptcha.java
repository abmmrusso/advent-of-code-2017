package net.aoc;

import java.util.stream.IntStream;

public class InverseCaptcha {
    public int captchaSolution(int[] captcha) {
        if(captcha == null || captcha.length < 2) {
            return 0;
        }

        return IntStream.range(0, captcha.length)
                .map(idx -> captcha[idx] == captcha[(idx + 1)%captcha.length]?captcha[idx]:0)
                .sum();
    }
}
