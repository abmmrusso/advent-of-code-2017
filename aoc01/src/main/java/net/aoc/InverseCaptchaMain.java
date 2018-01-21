package net.aoc;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.Optional;


public class InverseCaptchaMain {

    private final InverseCaptcha inverseCaptcha;

    public InverseCaptchaMain(InverseCaptcha inverseCaptcha) {
        this.inverseCaptcha = inverseCaptcha;
    }

    public void inverseCaptcha(InverseCaptchaArgs args) {
        if(args.getCycleFactor() == null) {
            args.setCycleFactor(args.getCaptcha() == null? 0: args.getCaptcha().length/2);
        }

        System.out.println(inverseCaptcha.captchaSolution(args.getCaptcha(), args.getCycleFactor()));
    }

    private int[] translateInputCaptchaToIntArray(String inputDigitSequence) {
        int[] digitSequence = null;
        if(inputDigitSequence != null) {
            digitSequence = inputDigitSequence.chars().map(c -> Integer.parseInt("" + (char)c)).toArray();
        }
        return digitSequence;
    }

    public static void main(String[] args) {
        InverseCaptchaArgs argsData = new InverseCaptchaArgs();
        try{
            argsData.processArguments(args);
            new InverseCaptchaMain(new InverseCaptcha()).inverseCaptcha(argsData);
        }catch(ParameterException pe) {
            argsData.printUsage();
        }
    }
}
