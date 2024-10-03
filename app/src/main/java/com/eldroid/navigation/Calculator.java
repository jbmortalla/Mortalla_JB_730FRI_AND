package com.eldroid.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Calculator extends Fragment {

    private TextView tvFormula, tvResult;
    private String formula = "";
    private boolean hasDecimal = false;
    private boolean operatorPressed = false;
    private double firstNumber = 0, secondNumber = 0;
    private String operator = "";
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        tvFormula = view.findViewById(R.id.tvFormula);
        tvResult = view.findViewById(R.id.tvResult);

        view.findViewById(R.id.zero).setOnClickListener(v -> appendToFormula("0"));
        view.findViewById(R.id.one).setOnClickListener(v -> appendToFormula("1"));
        view.findViewById(R.id.two).setOnClickListener(v -> appendToFormula("2"));
        view.findViewById(R.id.three).setOnClickListener(v -> appendToFormula("3"));
        view.findViewById(R.id.four).setOnClickListener(v -> appendToFormula("4"));
        view.findViewById(R.id.five).setOnClickListener(v -> appendToFormula("5"));
        view.findViewById(R.id.six).setOnClickListener(v -> appendToFormula("6"));
        view.findViewById(R.id.seven).setOnClickListener(v -> appendToFormula("7"));
        view.findViewById(R.id.eight).setOnClickListener(v -> appendToFormula("8"));
        view.findViewById(R.id.nine).setOnClickListener(v -> appendToFormula("9"));

        view.findViewById(R.id.dot).setOnClickListener(v -> addDecimal());
        view.findViewById(R.id.plus).setOnClickListener(v -> setOperator("+"));
        view.findViewById(R.id.mines).setOnClickListener(v -> setOperator("-"));
        view.findViewById(R.id.multiply).setOnClickListener(v -> setOperator("*"));
        view.findViewById(R.id.devide).setOnClickListener(v -> setOperator("/"));
        view.findViewById(R.id.equal).setOnClickListener(v -> calculateResult());

        view.findViewById(R.id.clear).setOnClickListener(v -> clearFormula());

        return view;
    }

    private void appendToFormula(String value) {
        if (operatorPressed && !formula.contains(operator)) {
            formula += " " + operator + " ";
        }
        formula += value;
        tvFormula.setText(formula);
    }

    private void addDecimal() {
        if (!hasDecimal) {
            if (operatorPressed && formula.endsWith(" ")) {
                formula += "0.";
            } else {
                formula += ".";
            }
            hasDecimal = true;
            tvFormula.setText(formula);
        }
    }

    private void setOperator(String op) {
        if (!operatorPressed && !formula.isEmpty()) {
            operator = op;
            operatorPressed = true;
            hasDecimal = false;
            tvFormula.setText(formula + " " + operator + " ");
        }
    }

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

                tvResult.setText(decimalFormat.format(result));
                formula = "";
                operatorPressed = false;
                operator = "";
                hasDecimal = false;
            } catch (NumberFormatException e) {
                tvResult.setText("Error");
            }
        }
    }

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