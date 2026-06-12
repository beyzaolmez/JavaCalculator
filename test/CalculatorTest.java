import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // --- add ---
    @Test void add_positives()         { assertEquals(8.0,  calculator.add(3, 5)); }
    @Test void add_negative()          { assertEquals(-2.0, calculator.add(-5, 3)); }
    @Test void add_zeros()             { assertEquals(0.0,  calculator.add(0, 0)); }

    // --- subtract ---
    @Test void subtract_basic()        { assertEquals(2.0,  calculator.subtract(5, 3)); }
    @Test void subtract_negative()     { assertEquals(-8.0, calculator.subtract(-5, 3)); }

    // --- multiply ---
    @Test void multiply_basic()        { assertEquals(15.0,  calculator.multiply(3, 5)); }
    @Test void multiply_negative()     { assertEquals(-15.0, calculator.multiply(-3, 5)); }
    @Test void multiply_zero()         { assertEquals(0.0,   calculator.multiply(0, 100)); }

    // --- divide ---
    @Test void divide_basic()          { assertEquals(2.5,  calculator.divide(5, 2)); }
    @Test void divide_negative()       { assertEquals(-2.5, calculator.divide(-5, 2)); }
    @Test void divide_by_zero()        { assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0)); }

    // --- modulus ---
    @Test void modulus_basic()         { assertEquals(1.0, calculator.modulus(9, 4)); }
    @Test void modulus_exact()         { assertEquals(0.0, calculator.modulus(8, 4)); }
    @Test void modulus_by_zero()       { assertThrows(ArithmeticException.class, () -> calculator.modulus(5, 0)); }

    // --- pow ---
    @Test void pow_positive()          { assertEquals(8.0,  calculator.pow(2, 3)); }
    @Test void pow_zero_exponent()     { assertEquals(1.0,  calculator.pow(5, 0)); }
    @Test void pow_negative_exp()      { assertEquals(0.25, calculator.pow(2, -2)); }

    // --- sqrt ---
    @Test void sqrt_perfect()          { assertEquals(3.0, calculator.sqrt(9)); }
    @Test void sqrt_zero()             { assertEquals(0.0, calculator.sqrt(0)); }
    @Test void sqrt_negative()         { assertThrows(ArithmeticException.class, () -> calculator.sqrt(-1)); }

    // --- Operation enum ---
    @Test void operation_fromSymbol_valid()    { assertNotNull(Operation.fromSymbol("+")); }
    @Test void operation_fromSymbol_invalid()  { assertNull(Operation.fromSymbol("?")); }
    @Test void operation_power_apply()         { assertEquals(8.0, Operation.POWER.apply(2, 3)); }
    @Test void operation_divide_by_zero()      { assertThrows(ArithmeticException.class, () -> Operation.DIVIDE.apply(5, 0)); }

    // --- InputParser ---
    @Test void parser_with_spaces()     { assertNotNull(InputParser.parse("3 + 5", 0)); }
    @Test void parser_without_spaces()  { assertNotNull(InputParser.parse("3+5", 0)); }
    @Test void parser_sqrt()            { assertNotNull(InputParser.parse("sqrt(9)", 0)); }
    @Test void parser_power()           { assertNotNull(InputParser.parse("2^8", 0)); }
    @Test void parser_ans_replacement() {
        ParsedExpression expr = InputParser.parse("ans + 5", 10.0);
        assertNotNull(expr);
        assertEquals(10.0, expr.a);
        assertEquals(5.0,  expr.b);
    }
    @Test void parser_invalid()         { assertNull(InputParser.parse("hello", 0)); }
}
