package net.aoc;

import com.beust.jcommander.IStringConverter;

public class CaptchaStringConverter implements IStringConverter<int[]> {

    @Override
    public int[] convert(String value) {
        int[] digitSequence = null;
        if(value != null) {
            digitSequence = value.chars().map(c -> Integer.parseInt("" + (char)c)).toArray();
        }
        return digitSequence;
    }
}
