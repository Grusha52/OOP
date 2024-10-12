package ru.nsu.chernikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;


/**
 * testing main and etc.
 */
public class MainTest {
    @Test
    void mainTest() {
        Main.main(null);
    }

    @Test
    void printTest() throws IOException {

        PrintStream oldOutput = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, false, "UTF-8"));
        Expression a = new Number(5);
        a.print();
        assertEquals("5", out.toString());
        out.reset();
        Expression exp2 = new Sub(new Variable("y"), new Number(5));

        exp2.print();

        assertEquals("(y-5)", out.toString());
        out.reset();
        Expression exp3 = new Div(new Add(new Number(10), new Variable("z")), new Number(2));

        exp3.print();
        assertEquals("((10+z)/2)", out.toString());
        out.reset();
        System.setOut(oldOutput);
    }

    @Test
    void evalTest() {
        Expression a = new Number(5);
        try {
            assertEquals(5, a.eval("x = 23"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }

        Expression exp1 = new Div(new Variable("y"), new Number(0));
        try {
            assertEquals(5, exp1.eval("y = 5; x = 1243"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }

        Expression exp2 = new Sub(new Variable("y"), new Number(5));
        try {
            assertEquals(0, exp2.eval("y = 5; x = 1243"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }

        Expression exp3 = new Div(new Add(new Number(10), new Variable("z")), new Number(2));
        try {
            assertEquals(15, exp3.eval("y = 5; x = 1243; z = 20"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }

    }

}