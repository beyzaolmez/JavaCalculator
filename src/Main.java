import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("=== Java Calculator ===");
        System.out.println("Operators: + | - | * | / | %");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter expression (e.g. 3 + 5): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                System.out.println("Invalid input. Please use format: <number> <operator> <number>\n");
                continue;
            }

            double a, b;
            try {
                a = Double.parseDouble(parts[0]);
                b = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers. Please try again.\n");
                continue;
            }

            String operator = parts[1];
            double result;

            try {
                switch (operator) {
                    case "+": result = calculator.add(a, b);      break;
                    case "-": result = calculator.subtract(a, b); break;
                    case "*": result = calculator.multiply(a, b); break;
                    case "/": result = calculator.divide(a, b);   break;
                    case "%": result = calculator.modulus(a, b);  break;
                    default:
                        System.out.println("Unknown operator '" + operator + "'. Use +, -, *, /, or %.\n");
                        continue;
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
                continue;
            }

            System.out.printf("Result: %s %s %s = %s%n%n",
                    format(a), operator, format(b), format(result));
        }

        scanner.close();
    }

    private static String format(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
