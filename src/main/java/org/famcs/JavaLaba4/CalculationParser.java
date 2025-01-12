package org.famcs.JavaLaba4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculationParser {

    public static String parseAndReplace(String input) {
        String regex = "(\\d+(\\.\\d+)?)\\s*([+\\-*/])\\s*(\\d+(\\.\\d+)?)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) 
        {
            double num1 = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(3);
            double num2 = Double.parseDouble(matcher.group(4));

            double operationResult = calculate(num1, num2, operator);
            if (operationResult % 1 == 0)
            {
                int intOperationResult = (int) operationResult;
                matcher.appendReplacement(result, String.valueOf(intOperationResult));
            }
            else{
                matcher.appendReplacement(result, String.valueOf(operationResult));
            }
            
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private static double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
