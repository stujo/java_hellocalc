package com.skillbox.demo;

import com.skillbox.demo.calc.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Hello world!
 */
public class HelloCalcApp {

    static final String numberPattern = "-?(\\d)*(\\.)?(\\d)+";

    public static void main(String[] args) {

        BufferedReader userInputReader = null;

        System.out.println("Hello from your friendly calculator!");
        Calculator calc = new Calculator();
        try {
            userInputReader = new BufferedReader(new InputStreamReader(System.in));
            boolean quitting = false;

            do {
                System.out.println("Enter a number to add (or '<return>' to quit)");

                String userInput = userInputReader.readLine();

                if (null == userInput || userInput.equals("")) {
                    quitting = true;
                } else if (userInput.matches(numberPattern)) {
                    try {
                        // Use the string constructor of the BigDecimal
                        // To get an exact representation of the userInput as decimal
                        BigDecimal toAdd = new BigDecimal(userInput);
                        System.out.println(String.format("Adding %s", toAdd.doubleValue()));
                        calc.add( toAdd.doubleValue());
                        System.out.println(String.format("The current total is %s", calc.getCurrentValue()));
                    } catch (NumberFormatException nfe) {
                        System.out.println("Sorry I couldn't understand that number");
                    }
                } else {
                    System.out.println("That doesn't look like a number, please try again");
                }
            }
            while (!quitting);

            System.out.println("The final total was " + calc.getCurrentValue());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (userInputReader != null) {
                try {
                    userInputReader.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
