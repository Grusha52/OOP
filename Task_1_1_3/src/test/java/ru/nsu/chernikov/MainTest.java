package ru.nsu.chernikov;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    void mainTest() {
        Main.main(null);
    }

    @Test
    void printTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression a = new Number(5);
        try {
            a.print(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("5", out.toString());
        out.reset();
        Expression exp2 = new Sub(new Variable("y"), new Number(5));

        try {
            exp2.print(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("(y-5)", out.toString());
        out.reset();
        Expression exp3 = new Div(new Add(new Number(10), new Variable("z")), new Number(2));

        try {
            exp3.print(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("((10+z)/2)", out.toString());
        out.reset();
    }

    @Test
    void evalTest() {
        Expression a = new Number(5);
        try {
            assertEquals(5, a.eval("x = 23"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        }

        Expression exp1 = new Div(new Variable("y"), new Number(0));
        try {
            assertEquals(5, exp1.eval("y = 5; x = 1243"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        }

        Expression exp2 = new Sub(new Variable("y"), new Number(5));
        try {
            assertEquals(0, exp2.eval("y = 5; x = 1243"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        }

        Expression exp3 = new Div(new Add(new Number(10), new Variable("z")), new Number(2));
        try {
            assertEquals(15, exp3.eval("y = 5; x = 1243; z = 20"));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        }

    }

}