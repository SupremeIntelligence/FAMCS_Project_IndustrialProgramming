package org.famcs.JavaLaba4;

import org.junit.jupiter.api.Test;

public class CalculationParserTest {
    @Test
    void testParseAndReplace() {
            String[] testCases = {
                "2 + 3",        
                "10 - 4",       
                "6 * 7",        
                "8 / 4",        
                "5 + 3.2",      
                "7.5 * 2",      
                "10 / 3",       
                "3 / 0"         
            };
    
            for (String input : testCases) {
                try {
                    String result = CalculationParser.parseAndReplace(input);
                    System.out.println("Input: " + input + " => Result: " + result);
                } catch (ArithmeticException e) {
                    System.out.println("Input: " + input + " => Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Input: " + input + " => Error: " + e.getMessage());
                }
            }
        }
    }

