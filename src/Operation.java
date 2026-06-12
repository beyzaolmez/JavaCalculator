import java.util.function.BiFunction;

public enum Operation {
    ADD("+",      (a, b) -> a + b),
    SUBTRACT("-", (a, b) -> a - b),
    MULTIPLY("*", (a, b) -> a * b),
    DIVIDE("/",   (a, b) -> {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
        return a / b;
    }),
    MODULUS("%",  (a, b) -> {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
        return a % b;
    }),
    POWER("^",    (a, b) -> Math.pow(a, b));

    private final String symbol;
    private final BiFunction<Double, Double, Double> fn;

    Operation(String symbol, BiFunction<Double, Double, Double> fn) {
        this.symbol = symbol;
        this.fn = fn;
    }

    public double apply(double a, double b) {
        return fn.apply(a, b);
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operation fromSymbol(String symbol) {
        for (Operation op : values()) {
            if (op.symbol.equals(symbol)) return op;
        }
        return null;
    }
}
