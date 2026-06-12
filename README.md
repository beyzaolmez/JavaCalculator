# JavaCalculator

A basic command-line calculator application written in Java.

## Features

- Addition (`+`)
- Subtraction (`-`)
- Multiplication (`*`)
- Division (`/`)
- Modulus (`%`)
- Division-by-zero error handling
- Clean integer display (e.g. `6` instead of `6.0`)

## Project Structure

```
JavaCalculator/
└── src/
    ├── Calculator.java   # Core arithmetic logic
    └── Main.java         # Interactive CLI entry point
```

## Requirements

- Java 8 or higher

## How to Run

**Compile:**
```bash
javac src/Calculator.java src/Main.java -d out
```

**Run:**
```bash
java -cp out Main
```

## Usage

```
=== Java Calculator ===
Operators: + | - | * | / | %
Type 'exit' to quit.

Enter expression (e.g. 3 + 5): 10 / 4
Result: 10 / 4 = 2.5

Enter expression (e.g. 3 + 5): 9 % 4
Result: 9 % 4 = 1

Enter expression (e.g. 3 + 5): exit
Goodbye!
```