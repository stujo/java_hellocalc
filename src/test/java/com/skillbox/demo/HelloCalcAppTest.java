package com.skillbox.demo;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Unit test for HelloCalcApp
 */
public class HelloCalcAppTest {


    static protected void implTestWithInput(String[] inputs, double expectedResult) {
        StringBuilder builder = new StringBuilder();
        for (String s : inputs) {
            builder.append(String.format("%s%n", s));
        }
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        try {
            InputStream testInput = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
            System.setIn(testInput);

            final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));

            String[] args = null;
            HelloCalcApp.main(args);

            final String standardOutput = myOut.toString();

            String expected = String.format("final total was %s", expectedResult);

            Assert.assertThat(standardOutput, containsString(expected));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }


    @Test
    public void testNoInput() {
        String[] empty = new String[0];
        implTestWithInput(empty, 0);
    }

    @Test
    public void test5And8Input() {
        String[] values = {"5","8"};
        implTestWithInput(values, 13);
    }

}
