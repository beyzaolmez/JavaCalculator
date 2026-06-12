# JavaCalculator

A command-line calculator application written in Java.

## Features

- **Operators**: `+` `-` `*` `/` `%` `^` (power)
- **Unary**: `sqrt(x)` — square root
- **`ans` keyword** — reuse the last result in the next expression
- **History** — view all past calculations with the `history` command
- **No-spaces input** — `3+5` and `3 + 5` both work
- **Decimal precision** — results rounded to 6 decimal places, trailing zeros trimmed
- **Error handling** — divide-by-zero, sqrt of negative number

## Project Structure

```
JavaCalculator/
├── pom.xml
├── src/
│   ├── Calculator.java        # Core arithmetic methods
│   ├── Operation.java         # Enum mapping symbols to operations
│   ├── InputParser.java       # Regex-based expression parser
│   ├── ParsedExpression.java  # Data class for parsed input
│   └── Main.java              # Interactive CLI entry point
└── test/
    └── CalculatorTest.java    # JUnit 5 unit tests
```

## Requirements

- Java 8 or higher
- Maven 3.x (for dependency management and tests)

## How to Run

**With Maven (recommended):**
```bash
mvn compile
mvn exec:java -Dexec.mainClass=Main
```

**With plain javac:**
```bash
javac src/*.java -d out
java -cp out Main
```

## How to Test

```bash
mvn test
```

## Usage

```
=== Java Calculator ===
Operators : + | - | * | / | % | ^
Unary     : sqrt(x)
Special   : 'ans' reuses last result, 'history' shows history, 'exit' quits

> 10 / 4
= 2.5

> ans * 2
= 5

> 2^8
= 256

> sqrt(144)
= 12

> history
--- History ---
  1. 10 / 4 = 2.5
  2. 2.5 * 2 = 5
  3. 2 ^ 8 = 256
  4. sqrt(144) = 12

> exit
Goodbye!
```