import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern BINARY_PATTERN = Pattern.compile(
            "^(-?\\d+(?:\\.\\d+)?)\\s*([+\\-*/%^])\\s*(-?\\d+(?:\\.\\d+)?)$"
    );
    private static final Pattern SQRT_PATTERN = Pattern.compile(
            "^sqrt\\((-?\\d+(?:\\.\\d+)?)\\)$", Pattern.CASE_INSENSITIVE
    );

    public static ParsedExpression parse(String input, double lastResult) {
        input = input.replaceAll("(?i)\\bans\\b", formatAns(lastResult)).trim();

        Matcher sqrt = SQRT_PATTERN.matcher(input);
        if (sqrt.matches()) {
            return new ParsedExpression(Double.parseDouble(sqrt.group(1)), "sqrt", null);
        }

        Matcher binary = BINARY_PATTERN.matcher(input);
        if (binary.matches()) {
            return new ParsedExpression(
                    Double.parseDouble(binary.group(1)),
                    binary.group(2),
                    Double.parseDouble(binary.group(3))
            );
        }

        return null;
    }

    private static String formatAns(double value) {
        return BigDecimal.valueOf(value).stripTrailingZeros().toPlainString();
    }
}
