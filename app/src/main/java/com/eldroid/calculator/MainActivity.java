package com.eldroid.calculator;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvFormula, tvResult;
    private String formula = "";
    private boolean hasDecimal = false;
    private boolean operatorPressed = false;
    private double firstNumber = 0, secondNumber = 0;
    private String operator = "";
    private DecimalFormat decimalFormat = new DecimalFormat("#.##"); // To allow only two decimal places

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFormula = findViewById(R.id.tvFormula);
        tvResult = findViewById(R.id.tvResult);

        // Example: Handling button clicks
        findViewById(R.id.zero).setOnClickListener(v -> appendToFormula("0"));
        findViewById(R.id.one).setOnClickListener(v -> appendToFormula("1"));
        findViewById(R.id.two).setOnClickListener(v -> appendToFormula("2"));
        findViewById(R.id.three).setOnClickListener(v -> appendToFormula("3"));
        findViewById(R.id.four).setOnClickListener(v -> appendToFormula("4"));
        findViewById(R.id.five).setOnClickListener(v -> appendToFormula("5"));
        findViewById(R.id.six).setOnClickListener(v -> appendToFormula("6"));
        findViewById(R.id.seven).setOnClickListener(v -> appendToFormula("7"));
        findViewById(R.id.eight).setOnClickListener(v -> appendToFormula("8"));
        findViewById(R.id.nine).setOnClickListener(v -> appendToFormula("9"));

        findViewById(R.id.dot).setOnClickListener(v -> addDecimal());
        findViewById(R.id.plus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.mines).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.devide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.equal).setOnClickListener(v -> calculateResult());

        findViewById(R.id.clear).setOnClickListener(v -> clearFormula());
    }

    // Method to append numbers to the formula
    private void appendToFormula(String value) {
        if (operatorPressed && !formula.contains(operator)) {
            formula += " " + operator + " "; // Add operator with spaces
        }
        formula += value;
        tvFormula.setText(formula);
    }

    // Handle decimal point addition
    private void addDecimal() {
        if (!hasDecimal) {
            if (operatorPressed && formula.endsWith(" ")) {
                formula += "0."; // In case the user pressed the operator and needs a decimal after
            } else {
                formula += ".";
            }
            hasDecimal = true;
            tvFormula.setText(formula);
        }
    }

    // Set the operator for calculation
    private void setOperator(String op) {
        if (!operatorPressed && !formula.isEmpty()) {
            operator = op;
            operatorPressed = true;
            hasDecimal = false; // Reset decimal flag for the second number
            tvFormula.setText(formula + " " + operator + " "); // Add operator with spaces
        }
    }

    // Perform the calculation when '=' is pressed
    private void calculateResult() {
        String[] parts = formula.split(" ");
        if (parts.length == 3) {
            try {
                firstNumber = Double.parseDouble(parts[0]);
                secondNumber = Double.parseDouble(parts[2]);

                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            tvResult.setText("Error");
                            return;
                        }
                        break;
                }

                // Display result with two decimal places
                tvResult.setText(decimalFormat.format(result));
                formula = ""; // Reset the formula for the next calculation
                operatorPressed = false;
                operator = "";
                hasDecimal = false;
            } catch (NumberFormatException e) {
                tvResult.setText("Error");
            }
        }
    }

    // Clear the formula and result
    private void clearFormula() {
        formula = "";
        operator = "";
        firstNumber = 0;
        secondNumber = 0;
        operatorPressed = false;
        hasDecimal = false;
        tvFormula.setText("");
        tvResult.setText("0");
    }
}