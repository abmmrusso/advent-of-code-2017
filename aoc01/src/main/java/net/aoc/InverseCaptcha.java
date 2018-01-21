package net.aoc;

import java.util.stream.IntStream;

public class InverseCaptcha {

    public int captchaSolution(int[] captcha, int captchaCycleFactor) {
        if(captcha == null || captcha.length == 0) {
            return 0;
        }

        if(captcha.length % 2 != 0) {
            throw new IllegalArgumentException();
        }

        return IntStream.range(0, captcha.length)
                .map(idx -> captcha[idx] == captcha[(idx + captchaCycleFactor)%captcha.length]?captcha[idx]:0)
                .sum();
    }
}
