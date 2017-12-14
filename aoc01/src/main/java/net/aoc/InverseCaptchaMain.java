package net.aoc;

import java.util.Optional;

public class InverseCaptchaMain {

    private InverseCaptcha inverseCaptcha;
    public InverseCaptchaMain(InverseCaptcha inverseCaptcha) {
        this.inverseCaptcha = inverseCaptcha;
    }

    public void inverseCaptcha(String inputDigitSequence) {
        int[] digitSequence = null;
        if(inputDigitSequence != null) {
            digitSequence = inputDigitSequence.chars().map(c -> Integer.parseInt("" + (char)c)).toArray();
        }

        System.out.println(inverseCaptcha.captchaSolution(digitSequence));
    }

    public static void main(String[] args) {
        new InverseCaptchaMain(new InverseCaptcha()).inverseCaptcha(args.length > 0? args[0]:null);
    }
}
