public class ParsedExpression {
    public final double a;
    public final String operator;
    public final Double b; // null for unary operations (e.g. sqrt)

    public ParsedExpression(double a, String operator, Double b) {
        this.a = a;
        this.operator = operator;
        this.b = b;
    }
}
