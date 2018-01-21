package net.aoc;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.*;

@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "commandParser" })
@ToString(exclude = { "commandParser" })
public @Data class InverseCaptchaArgs {

    public final static String CAPTCHA_OPTION = "--captcha";
    public final static String CYCLE_FACTOR_OPTION = "--cycleFactor";

    @Parameter(names = CAPTCHA_OPTION, description = "Captcha to reverse", converter = CaptchaStringConverter.class)
    private int[] captcha;
    @Parameter(names = CYCLE_FACTOR_OPTION, description = "Cycle factor to use when reversing captcha. By default it will be half the size of the provided captcha.")
    private Integer cycleFactor;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final JCommander commandParser;

    public InverseCaptchaArgs() {
        commandParser = JCommander.newBuilder().addObject(this).build();
        commandParser.setProgramName("");
    }

    public void processArguments(String[] args) {
        if(args != null) {
            commandParser.parse(args);
        }
    }

    public void printUsage() {
        commandParser.usage();
    }
}
