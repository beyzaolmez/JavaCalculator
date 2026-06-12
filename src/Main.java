import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int DECIMAL_PLACES = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        List<String> history = new ArrayList<>();
        double lastResult = 0;

        System.out.println("=== Java Calculator ===");
        System.out.println("Operators : + | - | * | / | % | ^");
        System.out.println("Unary     : sqrt(x)");
        System.out.println("Special   : 'ans' reuses last result, 'history' shows history, 'exit' quits\n");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.equalsIgnoreCase("history")) {
                if (history.isEmpty()) {
                    System.out.println("No history yet.\n");
                } else {
                    System.out.println("--- History ---");
                    for (int i = 0; i < history.size(); i++) {
                        System.out.printf("  %d. %s%n", i + 1, history.get(i));
                    }
                    System.out.println();
                }
                continue;
            }

            ParsedExpression expr = InputParser.parse(input, lastResult);
            if (expr == null) {
                System.out.println("Invalid input. Examples: 3+5, 10/4, 2^8, sqrt(9), ans*2\n");
                continue;
            }

            double result;
            String entry;
            try {
                if ("sqrt".equals(expr.operator)) {
                    result = calculator.sqrt(expr.a);
                    entry = String.format("sqrt(%s) = %s", format(expr.a), format(result));
                } else {
                    Operation op = Operation.fromSymbol(expr.operator);
                    if (op == null) {
                        System.out.println("Unknown operator: " + expr.operator + "\n");
                        continue;
                    }
                    result = op.apply(expr.a, expr.b);
                    entry = String.format("%s %s %s = %s",
                            format(expr.a), expr.operator, format(expr.b), format(result));
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
                continue;
            }

            lastResult = result;
            history.add(entry);
            System.out.println("= " + format(result) + "\n");
        }

        scanner.close();
    }

    static String format(double value) {
        double rounded = Math.round(value * Math.pow(10, DECIMAL_PLACES)) / Math.pow(10, DECIMAL_PLACES);
        if (rounded == Math.floor(rounded) && !Double.isInfinite(rounded)) {
            return String.valueOf((long) rounded);
        }
        String s = String.valueOf(rounded);
        if (s.contains(".")) {
            s = s.replaceAll("0+$", "").replaceAll("\\.$", "");
        }
        return s;
    }
}
